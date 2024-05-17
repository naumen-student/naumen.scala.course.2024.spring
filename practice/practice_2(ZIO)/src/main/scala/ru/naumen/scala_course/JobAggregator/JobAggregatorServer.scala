package ru.naumen.scala_course.JobAggregator

import cats.effect.{ExitCode => CatsExitCode}
import cats.syntax.all._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.CORS
import ru.naumen.scala_course.JobAggregator.Entities.dao.repositories.EntitiesRepository
import ru.naumen.scala_course.JobAggregator.Entities.dao.repositories.EntitiesRepository.EntitiesRepository
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.openapi.circe.yaml._
import sttp.tapir.server.http4s.ztapir.ZHttp4sServerInterpreter
import sttp.tapir.swagger.http4s.SwaggerHttp4s
import zio.blocking.Blocking
import zio.clock.Clock
import zio.interop.catz._
import zio.logging.slf4j.Slf4jLogger
import zio.logging.{Logging, _}
import zio.random.Random
import zio.{RIO, ZIO}
import ru.naumen.scala_course.JobAggregator.persistent.{DBTransactor, Liqui, LiquibaseService, performMigration}
import ru.naumen.scala_course.JobAggregator.http.HttpClient
import ru.naumen.scala_course.JobAggregator.configuration.{Config, Configuration}
import ru.naumen.scala_course.JobAggregator.user.dao.repositories.UserRepository
import ru.naumen.scala_course.JobAggregator.user.services.UserService
import ru.naumen.scala_course.JobAggregator.user.services.UserService.UserService

object JobAggregatorServer {
  type AppEnvironment = Configuration with Clock with Blocking with DBTransactor with LiquibaseService with EntitiesRepository
    with HttpClient with Logging  with Random with UserService

  type AppTask[A] = RIO[AppEnvironment, A]
  val logFormat = "[correlation-id = %s] %s"

  val serverName = """JobAggregator"""

  val loggingEnv =
    Slf4jLogger.make{(context, message) =>
      val correlationId = LogAnnotation.CorrelationId.render(
        context.get(LogAnnotation.CorrelationId)
      )
      logFormat.format(correlationId, message)
    }
  val transactorEnvironment = Configuration.live >+> Blocking.live >+> HttpClient.live >+> DBTransactor.live >+> loggingEnv

  val appEnvironment =  transactorEnvironment >+> 
    LiquibaseService.live >+> HttpClient.live ++
    Random.live >+> EntitiesRepository.live >+> UserRepository.live >+> UserService.live

  val server: ZIO[AppEnvironment, Throwable, Unit] = for{
    cfg <- zio.config.getConfig[Config]
    _ <- performMigration
    entityRepository <- ZIO.environment[EntitiesRepository].map(_.get)
    userService <- ZIO.environment[UserService].map(_.get)

    endpoints =
        (Entities.endpoints.endpoints(entityRepository)).toList ++
            (user.endpoints.endpoints(userService)).toList

    docs =  OpenAPIDocsInterpreter.serverEndpointsToOpenAPI(endpoints, s"$serverName server", "1.0").toYaml
    entitiesRoutes = ZHttp4sServerInterpreter.from[AppEnvironment]((Entities.endpoints.serverEndpoints(entityRepository)).toList).toRoutes
    userRoutes = ZHttp4sServerInterpreter.from[AppEnvironment](user.endpoints.serverEndpoints(userService).toList).toRoutes

    httpApp = Router("" -> (entitiesRoutes <+> userRoutes <+> new SwaggerHttp4s(docs).routes)).orNotFound
    server <- ZIO.runtime[AppEnvironment].flatMap { implicit rts =>
      val ec = rts.platform.executor.asEC
      BlazeServerBuilder[AppTask](ec)
        .bindHttp(cfg.api.port, cfg.api.host)
        .withHttpApp(CORS(httpApp))
        .serve
        .compile[AppTask, AppTask, CatsExitCode]
        .drain
    }
  } yield server
}
