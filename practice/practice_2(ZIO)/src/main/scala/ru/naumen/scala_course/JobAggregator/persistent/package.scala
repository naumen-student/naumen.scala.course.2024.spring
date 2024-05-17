package ru.naumen.scala_course.JobAggregator

import cats.effect.Blocker
import doobie.Transactor
import doobie.hikari.HikariTransactor
import doobie.quill.DoobieContext
import io.getquill.{Escape, Literal, NamingStrategy}
import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.{ClassLoaderResourceAccessor, CompositeResourceAccessor, FileSystemResourceAccessor}
import ru.naumen.scala_course.JobAggregator.configuration.{Config, Configuration, DbConfig}
import ru.naumen.scala_course.JobAggregator.persistent.DBTransactor.dbTransactor
import ru.naumen.scala_course.JobAggregator.persistent.LiquibaseService.Service
import zio.blocking.{Blocking, blockingExecutor}
import zio.interop.catz._
import zio._

import scala.concurrent.ExecutionContext

package object persistent {

  type DBTransactor = Has[Transactor[Task]]

  type LiquibaseService = Has[Service]

  type Liqui = Has[Liquibase]

  object LiquibaseService {

    trait Service {
      def performMigration: Task[Unit]
    }

    class Impl(liq: Liquibase) extends Service {

      override def performMigration: Task[Unit] = ZIO.effect(liq.update("dev"))
    }

    def mkLiquibase(config: Config, transactor: Transactor[Task]): ZManaged[Any, Throwable, Liquibase] = for {
      connection <- transactor.connect(transactor.kernel).toManagedZIO
      fileAccessor = new FileSystemResourceAccessor()
      classLoader = classOf[LiquibaseService].getClassLoader
      classLoaderAccessor = new ClassLoaderResourceAccessor(classLoader)
      fileOpener = new CompositeResourceAccessor(fileAccessor, classLoaderAccessor)
      liqui = new Liquibase(config.liquibase.changeLog, fileOpener, new JdbcConnection(connection))
    } yield (liqui)


    val liquibaseLayer: ZLayer[DBTransactor with Configuration, Throwable, Liqui] = ZLayer.fromManaged(
      for {
        config <- zio.config.getConfig[Config].toManaged_
        transactor <- dbTransactor.toManaged_
        liquibase <- mkLiquibase(config, transactor)
      } yield (liquibase)
    )


    val live: ZLayer[DBTransactor with Configuration, Throwable, LiquibaseService] =
      liquibaseLayer >>> ZLayer.fromService[Liquibase, LiquibaseService.Service](liq => new Impl(liq))

  }

  object DBTransactor {

    val doobieContext = new DoobieContext.Postgres(NamingStrategy(Escape, Literal)) // Literal naming scheme

    def mkTransactor(conf: DbConfig, connectEC: ExecutionContext, transactEC: ExecutionContext): Managed[Throwable, Transactor[Task]] =
      HikariTransactor.newHikariTransactor[Task](
        conf.driver,
        conf.url,
        conf.user,
        conf.password,
        connectEC,
        Blocker.liftExecutionContext(transactEC)
      ).toManagedZIO

    val live: ZLayer[Configuration with Blocking, Throwable, DBTransactor] = ZLayer.fromManaged(
      (for {
        config <- zio.config.getConfig[Config].toManaged_
        executor <- blockingExecutor.toManaged_
        transactor <- DBTransactor.mkTransactor(config.db, executor.asEC, executor.asEC)
      } yield transactor)
    )

    def dbTransactor: URIO[DBTransactor, Transactor[Task]] = ZIO.service[Transactor[Task]]
  }

  def performMigration: ZIO[LiquibaseService, Throwable, Unit] = ZIO.service[Service].flatMap(_.performMigration)
}
