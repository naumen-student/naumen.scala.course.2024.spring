@@ -8,18 +8,44 @@
import scala.util.{Failure, Success, Try}

object Task5 extends App {

  import Task4.MonadError

  sealed trait MyEither[+E, +A] {
    def isError: Boolean
  }

  object MyEither {
    case class Right[+E, +A](value: A) extends MyEither[E, A] {
      override def isError: Boolean = false
    }

    case class Left[+E, +A](value: E) extends MyEither[E, A] {
      override def isError: Boolean = true
    }

    def apply[A](value: A): MyEither[Nothing, A] = Right(value)

    def error[E, A](error: E): MyEither[E, A] = Left(error)

    def possibleError[A](f: => A): MyEither[Throwable, A] = Try(f) match {
      case Failure(exception) => Left(exception)
      case Success(value) => Right(value)
    }

    implicit def myEitherMonad[E]: MonadError[MyEither, E] = new MonadError[MyEither, E] {
      override def pure[A](value: A): MyEither[E, A] = MyEither(value)

      override def flatMap[A, B](fa: MyEither[E, A])(f: A => MyEither[E, B]): MyEither[E, B] = fa match {
        case Right(value) => f(value)
        case Left(value) => Left(value)
      }

      override def raiseError[A](fa: MyEither[E, A])(error: => E): MyEither[E, A] = Left(error)

      override def handleError[A](fa: MyEither[E, A])(handle: E => A): MyEither[E, A] = fa match {
        case Right(value) => Right(value)
        case Left(value) => Right(handle(value))
      }
    }
  }
}
