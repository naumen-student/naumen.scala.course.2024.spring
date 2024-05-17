package ru.naumen.scala_course.JobAggregator

package object constants {
  object serverMessages{
    val ServerError = "ServerError"
    val MethodNotAllowed = "MethodNotAllowed"
    val WrongRequestError = "WrongRequestError"
  }

  object pageNumbers{
    val defaultPageNumber = 1
    val defaultPageSize = 10
    val pageSizeList = Vector(6, 10, 20, 50, 100, 150)
  }

  object sorting{
    val ascSortDirection = "asc"
    val descSortDirection = "desc"
  }

}
