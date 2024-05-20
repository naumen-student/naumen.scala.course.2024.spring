import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

import java.time.LocalDateTime

object Main extends ZIOAppDefault {

  def foo(start: Int) = for {
    _ <- ZIO.succeed {
      var currentThread = Thread.currentThread().getName
      var i: Long = 0
      var s: BigInt = start

      while (i < 100000) {
        s = s + BigInt(i).pow(2)
        i = i + 1
      }
      s
    }
  } yield ()

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = for {
    _ <- ZIO.succeed(println(LocalDateTime.now()))
    _ <- (1 to 10000).map(n => foo(n)).reduce((z1, z2) => z1.zipPar(z2))
    _ <- ZIO.succeed(println(LocalDateTime.now()))
  } yield ()

}