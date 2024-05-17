package ru.naumen.scala_course.JobAggregator.user

import cats.data.NonEmptyList
import ru.naumen.scala_course.JobAggregator.dto.ResponseDTO
import ru.naumen.scala_course.JobAggregator.endpoints.toResponse
import ru.naumen.scala_course.JobAggregator.persistent.DBTransactor
import ru.naumen.scala_course.JobAggregator.user.doc.UserDoc
import ru.naumen.scala_course.JobAggregator.user.services.UserService
import zio.logging.Logging
import zio.random.Random
import sttp.tapir.ztapir._

package object endpoints {

    def serverEndpoints[R <: DBTransactor with Logging with Random](userService: UserService.Service) = {
        NonEmptyList.of(
            UserDoc.findUserEndpoint.zServerLogic[R] { userId =>
                toResponse {
                    userService.findBy(userId)
                } (res => ResponseDTO.ok(res))
            }
        )
    }

    def endpoints[R <: DBTransactor with Logging with Random](userService: UserService.Service) =
        serverEndpoints(userService)

}
