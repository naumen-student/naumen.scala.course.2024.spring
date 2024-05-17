package ru.naumen.scala_course.JobAggregator

import io.circe.Codec
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveConfiguredCodec
import ru.naumen.scala_course.JobAggregator.constants.serverMessages
import ru.naumen.scala_course.JobAggregator.endpoints.WithStatusCodeError
import sttp.model.StatusCode

package object errorsModel {
  sealed trait JobAggregatorError extends WithStatusCodeError

  object JobAggregatorError {

    case class NotAllowed (message: String) extends JobAggregatorError{
      override def statusCode: StatusCode = StatusCode.MethodNotAllowed

      override def code: String = serverMessages.MethodNotAllowed

      override def getMessage: String = message
    }

    case class ResourceNotFound(message: String) extends JobAggregatorError {
      override def statusCode: StatusCode = StatusCode.Ok

      override def getMessage: String = message

      override def code: String = "NotFound"
    }

    case class NotFound(message: String) extends JobAggregatorError {
      override def statusCode: StatusCode = StatusCode.NotFound

      override def getMessage: String = message

      override def code: String = "NotFound"
    }

    case class Forbidden(message: String = "Доступ запрещен") extends JobAggregatorError {
      override def statusCode: StatusCode = StatusCode.Forbidden

      override def getMessage: String = message

      override def code: String = "Forbidden"
    }

    case class InternalError (message: String) extends JobAggregatorError{
      override def statusCode: StatusCode = StatusCode.InternalServerError

      override def code: String = serverMessages.ServerError

      override def getMessage: String = message
    }


    case class ActionNotAllowed(message: String) extends JobAggregatorError {
      override def statusCode: StatusCode = StatusCode.Ok

      override def getMessage: String = message

      override def code: String = "ActionNotAllowed"
    }

    implicit private lazy val circeConfig: Configuration = Configuration.default.withDefaults.withDiscriminator("type")

    implicit val codec: Codec[JobAggregatorError] = deriveConfiguredCodec[JobAggregatorError]

  }
}
