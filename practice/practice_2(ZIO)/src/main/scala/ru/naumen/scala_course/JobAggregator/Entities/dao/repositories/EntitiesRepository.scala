package ru.naumen.scala_course.JobAggregator.Entities.dao.repositories

import doobie.quill.DoobieContext
import io.getquill.{CompositeNamingStrategy2, Escape, Literal}
import ru.naumen.scala_course.JobAggregator.constants
import ru.naumen.scala_course.JobAggregator.persistent.DBTransactor
import ru.naumen.scala_course.JobAggregator.Entities.dao.entities.{Entities, EntitiesId}
import zio._
import zio.interop.catz._
import doobie.implicits._
import ru.naumen.scala_course.JobAggregator.Entities.EntitiesDTO
import ru.naumen.scala_course.JobAggregator.errorsModel.JobAggregatorError
import zio.logging.Logging
import ru.naumen.scala_course.JobAggregator.utils.ZioExtension.ExtendedZio

import java.time.{LocalDate, LocalDateTime}

object EntitiesRepository {
  val dc: DoobieContext.Postgres[CompositeNamingStrategy2[Escape.type, Literal.type]] = DBTransactor.doobieContext
  import dc._
  type EntitiesRepository = Has[Service]

  trait Service{
    def getById(id: EntitiesId): ZIO[DBTransactor, JobAggregatorError, Option[EntitiesDTO]]
  }

  class ServiceImpl extends Service {

    private val schema = quote {
      querySchema[Entities](""""Entities"""")
    }

    def getById(id: EntitiesId): ZIO[DBTransactor, JobAggregatorError, Option[EntitiesDTO]] = (for {
      transactor <- DBTransactor.dbTransactor

      entity <- dc.run(quote(schema.filter(item => item.id == lift(id.id)).take(1)))
          .map(_.headOption)
          .transact(transactor)
          .orJobAggregatorActionNotAllowed
    } yield {
      entity.map(e => EntitiesDTO(e.id, e.name))
    })

  }

  val live: ULayer[EntitiesRepository] = ZLayer.succeed(new ServiceImpl)

}
