package ru.naumen.scala_course.JobAggregator.utils

import ru.naumen.scala_course.JobAggregator.errorsModel.JobAggregatorError.ActionNotAllowed
import zio.ZIO

object ZioExtension{
  implicit class ExtendedZio[R1, Any](val zio: ZIO[R1, Throwable, Any]) {

    def orJobAggregatorActionNotAllowed: ZIO[R1, ActionNotAllowed, Any] = zio.mapError { ex: Throwable => ActionNotAllowed(ex.getMessage) }
  }
}
