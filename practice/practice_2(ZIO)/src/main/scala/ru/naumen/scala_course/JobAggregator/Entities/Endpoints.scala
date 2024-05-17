package ru.naumen.scala_course.JobAggregator.Entities

import ru.naumen.scala_course.JobAggregator.Entities.dao.entities.Entities
import ru.naumen.scala_course.JobAggregator.dto.ResponseDTO
import sttp.tapir.Endpoint
import ru.naumen.scala_course.JobAggregator.endpoints._
import ru.naumen.scala_course.JobAggregator.errorsModel.JobAggregatorError
import sttp.tapir.generic.auto._
import sttp.tapir.ztapir._

object Endpoints extends Doc {
    private val tag = "Entities"

    def getBy: Endpoint[String, Err, ResponseDTO[Option[EntitiesDTO]], Any] =
        baseApiPublicEndpoint[JobAggregatorError].get
            .tag(tag)
            .in("get" / path[String]("id"))
            .summary("Получить сущность по id")
            .out(jsonBody[ResponseDTO[Option[EntitiesDTO]]])
}
