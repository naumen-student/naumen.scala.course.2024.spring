package ru.naumen.scala_course.JobAggregator.endpoints

import io.circe.{Codec, Decoder, Encoder}
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveConfiguredCodec
import ru.naumen.scala_course.JobAggregator.constants.serverMessages
import sttp.model.StatusCode

import scala.reflect.ClassTag

sealed trait ErrorResult extends Throwable

object ErrorResult {

  case class InternalError(success: Boolean, code: String, message: String) extends ErrorResult {
    override def getMessage: String = s"На сервере произошла непредвиденная ошибка: $message"
  }

  case class GenericError(success: Boolean, code: String, message: String) extends ErrorResult {
    override def getMessage: String = message
  }

  def apply[E <: WithStatusCodeError](error: Throwable)(implicit classTag: ClassTag[E]): (StatusCode, ErrorResult) =
    if (classTag.runtimeClass.isInstance(error))
      error.asInstanceOf[E].statusCode  -> GenericError(success = false, error.asInstanceOf[E].code, error.getMessage)
    else StatusCode.InternalServerError -> InternalError(success = false, serverMessages.ServerError, error.getMessage)

  implicit private val circeConfig: Configuration = Configuration.default.withDefaults.withDiscriminator("type")

  implicit def codec[T: Encoder: Decoder]: Codec[ErrorResult] = deriveConfiguredCodec
}

trait WithStatusCodeError extends Throwable {
  def statusCode: StatusCode
  def code: String
}
