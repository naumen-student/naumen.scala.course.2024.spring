import java.time.LocalDateTime
import java.util.concurrent.{Executors, ForkJoinPool}
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future}

object MainScala {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(new ForkJoinPool(10000))

  def foo(start: Int = 0) = {
    var currentThread = Thread.currentThread().getName
    var i: Long = 0
    var s: BigInt = start

    while (i < 100000) {
      s = s + BigInt(i).pow(2)
      i = i + 1
    }
  }

  def main(args: Array[String]): Unit = {
    println(LocalDateTime.now())

    val calc = Future.sequence((1 to 10000).map(n => Future(foo(n))))

    Await.result(calc, 360.seconds)

    println(LocalDateTime.now())
  }

}
