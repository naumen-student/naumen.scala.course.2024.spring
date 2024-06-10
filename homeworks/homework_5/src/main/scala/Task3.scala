import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

@@ -11,6 +10,7 @@



object Task3 extends App {
  def mapReduce[A, B: Monoid](values: Vector[A])(func: A => B): Future[B] = {
    val numCores = Runtime.getRuntime.availableProcessors
    @@ - 25, 8 + 25, 20 @@
    object Task3 extends App {
      case class Count(word: String, count: Int)

      case class WordsCount(count: Seq[Count])

      object WordsCount {
        implicit val monoid: Monoid[WordsCount] = new Monoid[WordsCount] {
          override def empty: WordsCount = WordsCount(Seq.empty)

          override def combine(x: WordsCount, y: WordsCount): WordsCount = WordsCount(
            (x.count ++ y.count)
              .groupBy(z => z.word)
              .map(word => Count(word._1, word._2.map(item => item.count).sum))
              .toSeq
          )
        }
      }


      def countWords(lines: Vector[String]): WordsCount = {
        val words = lines.flatMap(_.split(" "))
        val words_count = mapReduce(words)(word => WordsCount(Seq(Count(word, 1))))
        Await.result(words_count, 10.second)
      }
    }
  }
}
