import scala.runtime.Nothing$
import scala.util.{Failure, Success, Try}

/*
  Задание №5
  Задание аналогично предыдущему задания, но теперь мы уходим от использования стандартного Either.
  Нужно:
  1) Доделать реализацию MyEither (нужны аналоги Right и Left)
  2) Написать для MyEither инстанс MonadError
  3) Написать функции apply, error, possibleError
 */
object Task5 extends App {
  import Task4.MonadError

  sealed trait MyEither[+E, +A] {
    def isError: Boolean
    def left: E
    def right: A
    override def equals(other: Any): Boolean = other match {
      case person: MyEither[E,A] =>
        this.isError == person.isError && this.right == person.right
      case _ => false
    }
  }
  object MyEither {


    def apply[A](value: A): MyEither[Nothing, A] = new MyEither[Nothing, A] {
      override def isError: Boolean = false
      override def left: Nothing = ???
      override def right: A = value
    }
    def error[E, A](error: E): MyEither[E, A] = new MyEither[E, A] {
      override def isError: Boolean = true
      override def left: E = error
      override def right: Nothing = ???
    }
    def possibleError[A](f: => A): MyEither[Throwable, A] = {
      try {
        apply(f)
      }
      catch {
        case e : Throwable => error(e)
      }
    }

    implicit def myEitherMonad[E]: MonadError[MyEither, E] = new MonadError[MyEither, E] {

      override def pure[A](value: A): MyEither[E, A] = apply(value)

      override def flatMap[A, B](fa: MyEither[E, A])(f: A => MyEither[E, B]): MyEither[E, B]
      = if (!fa.isError) f(fa.right)
      else new MyEither[E, B] {
        override def isError: Boolean = true

        override def left: E = fa.left

        override def right: Nothing = ???
      }

      override def raiseError[A](fa: MyEither[E, A])(error: => E): MyEither[E, A] = MyEither.error(error)

      override def handleError[A](fa: MyEither[E, A])(handle: E => A): MyEither[E, A] = {
        val newValue = handle(fa.left)
        apply(newValue)
      }
    }
  }

  object MyEitherSyntax {
    implicit class MyEitherOps[E, A](val either: MyEither[E, A]) {
      def flatMap[B](f: A => MyEither[E, B]): MyEither[E, B] =
        MyEither.myEitherMonad[E].flatMap(either)(f)

      def map[B](f: A => B): MyEither[E, B] = MyEither.myEitherMonad.map(either)(f)

      def handleError(f: E => A): MyEither[E, A] =
        MyEither.myEitherMonad.handleError(either)(f)
    }
  }
}
