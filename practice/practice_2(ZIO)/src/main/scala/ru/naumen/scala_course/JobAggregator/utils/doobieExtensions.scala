package ru.naumen.scala_course.JobAggregator.utils

import doobie.quill.DoobieContext
import io.getquill.{CompositeNamingStrategy2, Escape, Literal}
import ru.naumen.scala_course.JobAggregator.persistent.DBTransactor

object doobieExtensions {
  val dc: DoobieContext.Postgres[CompositeNamingStrategy2[Escape.type, Literal.type]] = DBTransactor.doobieContext
  import dc._
  implicit class doobieResult[A](result: Result[A]){
    def *>[B](that: Result[B]): Result[B] = result.flatMap(_ => that)
    def unit: Result[Unit] = result.map(_ => {})
  }
}
