package ru.naumen.scala_course.JobAggregator.persistent

import doobie.util.transactor.Transactor
import liquibase.change.custom.CustomTaskChange
import liquibase.database.Database
import liquibase.exception.ValidationErrors
import liquibase.resource.ResourceAccessor
import ru.naumen.scala_course.JobAggregator.JobAggregatorServer.transactorEnvironment
import ru.naumen.scala_course.JobAggregator.configuration
import ru.naumen.scala_course.JobAggregator.configuration.Config
import zio.interop.catz.zioResourceSyntax
import zio.{IO, RIO, Task, ZManaged, _}


trait DoobieCustomTaskChange extends CustomTaskChange {
  val runtime: Runtime[zio.ZEnv] = Runtime.default

  def performMigrations(config: Config, transactor: Transactor[Task]): Task[Unit]

  override def execute(database: Database): Unit = {

    val resources = for {
      config <- zio.config.getConfig[Config].toManaged_
      transactor <- DBTransactor.dbTransactor.toManaged_
      connection <- transactor.connect(transactor.kernel).toManagedZIO
      oldAutoCommit = connection.getAutoCommit
      connectionResource <- ZManaged.makeEffect(connection){in =>
          in.setAutoCommit(oldAutoCommit)
          in.close()
      }
    } yield (config, transactor, connectionResource)

   val runnable = resources.use { case (config, tr, conn) =>
     performMigrations(config, tr) *> IO.effect(conn.setAutoCommit(false)) *> IO.effect(conn.commit())
       .onError(_ => UIO.effectTotal(conn.rollback()))
   }.provideSomeLayer[ZEnv](transactorEnvironment)

    runtime.unsafeRun(runnable)
  }


  override def getConfirmationMessage: String = "success"

  override def validate(database: Database): ValidationErrors = new ValidationErrors()

  override def setUp(): Unit = {}

  override def setFileOpener(resourceAccessor: ResourceAccessor): Unit = {}
}
