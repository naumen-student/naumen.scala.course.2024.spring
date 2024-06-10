@@ -11,37 +11,37 @@
import scala.util.{Failure, Success, Try}



object Task4 extends App {
  trait MonadError[F[_, _], E] {
    def pure[A](value: A): F[E, A]

    def flatMap[A, B](fa: F[E, A])(f: A => F[E, B]): F[E, B]

    def map[A, B](fa: F[E, A])(f: A => B): F[E, B] = flatMap(fa)(a => pure(f(a)))

    def raiseError[A](fa: F[E, A])(error: => E):  F[E, A]

    def handleError[A](fa: F[E, A])(handle: E => A): F[E, A]
  }

  case class EIO[+E, +A](value: Either[E, A])
  object EIO {
    def apply[A](value: A): EIO[Nothing, A] = EIO[Nothing, A](Right(value))

    def error[E, A](error: E): EIO[E, A] = EIO[E, A](Left(error))

    def possibleError[A](f: => A): EIO[Throwable, A] = EIO(Try(f).toEither)

    implicit def monad[E]: MonadError[EIO, E] = new MonadError[EIO, E] {
      override def pure[A](value: A): EIO[E, A] = EIO.apply(value)
      override def flatMap[A, B](fa: EIO[E, A])(f: A => EIO[E, B]): EIO[E, B] =
        EIO.apply(fa.value.flatMap(a => f(a).value))
      override def raiseError[A](fa: EIO[E, A])(error: => E): EIO[E, A] = EIO.error[E, A](error)
      override def handleError[A](fa: EIO[E, A])(handle: E => A): EIO[E, A] = EIO(fa.value.fold(handle, identity))
    }
  }

  object EIOSyntax {
    implicit class EIOOps[E, A](val eio: EIO[E, A]) {
      def flatMap[B](f: A => EIO[E, B]): EIO[E, B] =
        EIO.monad[E].flatMap(eio)(f)

      def map[B](f: A => B): EIO[E, B] = EIO.monad.map(eio)(f)

      def handleError(f: E => A): EIO[E, A] =
        EIO.monad.handleError(eio)(f)
    }
  }
}