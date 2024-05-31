package databaseConnectionUtils

import databaseConnectionUtils.monads.Monad

sealed trait QueryResult[T] {
    def fold[B](successF: T => B, errorF: String => B): B =
        this match {
            case SuccessResult(res) => successF(res)
            case FailureResult(errorMsg) => errorF(errorMsg)
        }
}

case class SuccessResult[T](result: T) extends QueryResult[T]

case class FailureResult[T](errorMsg: String) extends QueryResult[T]

object FailureResult {
    def from[T](error: Throwable, sql: String): FailureResult[T] =
        FailureResult("Query \n" + sql + "\nfailed with message\n" + error.getMessage)
}

object QueryResult {
    private val QueryResultMonad: Monad[QueryResult] = new Monad[QueryResult] {
        override def pure[A](el: A): QueryResult[A] = SuccessResult(el)

        override def map[A, B](obj: QueryResult[A])(f: A => B): QueryResult[B] =
            obj match {
                case SuccessResult(res) => SuccessResult(f(res))
                case FailureResult(errorMsg) => FailureResult(errorMsg)
            }

        override def flatMap[A, B](obj: QueryResult[A])(f: A => QueryResult[B]): QueryResult[B] =
            obj match {
                case SuccessResult(res) => f(res)
                case FailureResult(errorMsg) => FailureResult(errorMsg)
            }
    }

    implicit class QueryResultMonadExt[T](queryResult: QueryResult[T]) {
        def pure(el: T): QueryResult[T] = QueryResultMonad.pure(el)

        def map[B](f: T => B): QueryResult[B] = QueryResultMonad.map(queryResult)(f)

        def flatMap[B](f: T => QueryResult[B]): QueryResult[B] = QueryResultMonad.flatMap(queryResult)(f)
    }
}