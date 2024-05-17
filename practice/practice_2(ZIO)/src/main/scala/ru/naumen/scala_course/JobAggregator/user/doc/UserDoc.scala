package ru.naumen.scala_course.JobAggregator.user.doc

import ru.naumen.scala_course.JobAggregator.dto.ResponseDTO
import ru.naumen.scala_course.JobAggregator.endpoints.{Doc, baseApiPublicEndpoint}
import ru.naumen.scala_course.JobAggregator.errorsModel.JobAggregatorError
import ru.naumen.scala_course.JobAggregator.user.services.dtos.UserDTO
import sttp.tapir.Endpoint
import sttp.tapir.generic.auto.schemaForCaseClass
import sttp.tapir.ztapir._

object UserDoc extends Doc {

    def findUserEndpoint: Endpoint[String, Err, ResponseDTO[UserDTO], Any] =
        baseApiPublicEndpoint[JobAggregatorError]
            .get
            .tag("User")
            .in("user" / path[String]("userId"))
            .out(jsonBody[ResponseDTO[UserDTO]])
            .summary("получение пользователя по id")

    def addUserEndpoint: Endpoint[UserDTO, Err, ResponseDTO[Unit], Any] =
        baseApiPublicEndpoint[JobAggregatorError]
            .post
            .tag("User")
            .in(jsonBody[UserDTO])
            .out(jsonBody[ResponseDTO[Unit]])
            .summary("добавление пользователя")

}
