package ru.naumen.scala_course.JobAggregator.Entities

import cats.data.NonEmptyList
import ru.naumen.scala_course.JobAggregator.Entities.dao.entities.EntitiesId
import ru.naumen.scala_course.JobAggregator.Entities.dao.repositories.EntitiesRepository
import ru.naumen.scala_course.JobAggregator.dto.ResponseDTO
import ru.naumen.scala_course.JobAggregator.endpoints.{toResponse, toResponse_}
import ru.naumen.scala_course.JobAggregator.persistent.DBTransactor
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.ztapir._
import zio.{RIO, UIO, ZIO}
import zio.logging.Logging
import zio.random.Random
import zio.{Has, Task, UIO, URIO, ZIO, ZLayer}


package object endpoints {
  def serverEndpoints[R <: DBTransactor with Logging with Random](
                                                  entityService: EntitiesRepository.Service
                                               ) = {

    NonEmptyList.of(
      Endpoints.getBy.zServerLogic[R] {
        case id => toResponse {
          entityService.getById(EntitiesId(id))
        }(entity => ResponseDTO.ok(entity))
      }
    )
  }

  def endpoints[R <: DBTransactor with Logging with Random](entityService: EntitiesRepository.Service): NonEmptyList[ServerEndpoint[_, _, _, Any, RIO[R, *]]] =
    serverEndpoints(entityService)
}
