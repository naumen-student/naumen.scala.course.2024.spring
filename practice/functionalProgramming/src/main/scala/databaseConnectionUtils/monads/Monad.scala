package databaseConnectionUtils.monads

trait Monad[T[_]] {
    def pure[A](el: A): T[A]
    def map[A, B](obj: T[A])(f: A => B): T[B]
    def flatMap[A, B](obj: T[A])(f: A => T[B]): T[B]
}
