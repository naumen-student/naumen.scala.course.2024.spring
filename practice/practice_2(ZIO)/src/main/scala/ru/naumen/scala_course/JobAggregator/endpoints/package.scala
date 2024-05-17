package ru.naumen.scala_course.JobAggregator

import io.circe.{Decoder, Encoder}
import sttp.model.StatusCode
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.ztapir._
import sttp.tapir.{Endpoint, Schema, Validator}
import zio.ZIO

import scala.reflect.ClassTag

package object endpoints {

  def baseApiPublicEndpoint[E: Encoder: Decoder: Schema]: Endpoint[Unit, (StatusCode, ErrorResult), Unit, Any] =
    endpoint.in("api" / "v1").errorOut(statusCode).errorOut(jsonBody[ErrorResult])

  def toResponse[E <: WithStatusCodeError]: ToResponse[E] = new ToResponse[E]

  def toResponse_[E <: WithStatusCodeError, A, R](action: ZIO[R, E, A])(implicit classTag: ClassTag[E]): ZIO[R, (StatusCode, ErrorResult), A] =
    new ToResponse[E].apply[A, R, A](action)(v => v)

  class ToResponse[E <: WithStatusCodeError] {
    def apply[A, R, B](action: ZIO[R, E, A])(onSuccess: A => B)(implicit classTag: ClassTag[E]) : ZIO[R, (StatusCode, ErrorResult), B] = {
      action.bimap(
        err => ErrorResult(err),
        v => onSuccess(v)
      )
    }
  }
}
