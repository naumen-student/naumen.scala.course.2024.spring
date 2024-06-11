import cats._
import cats.implicits._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

/*
  Задание №3
  Всё просто, нужно посчитать количество строк.
  Реализуйте функцию countWords, которая принимает список строк.
  Обязательно использовать функцию mapReduce.
 */
object Task3 extends App {
  def mapReduce[A, B: Monoid](values: Vector[A])(func: A => B): Future[B] = {
    val numCores = Runtime.getRuntime.availableProcessors
    val groupSize = (1.0 * values.size / numCores).ceil.toInt
    values
      .grouped(groupSize)
      .toVector
      .traverse(group => Future(group.foldMap(func)))
      .map(_.combineAll)
  }

  case class Count(word: String, count: Int)
  case class WordsCount(count: Seq[Count])
  object WordsCount {
    implicit val monoid: Monoid[WordsCount] = new Monoid[WordsCount] {
      override def empty() = new WordsCount(Count("", 0):: Nil)
      override def combine(v1: WordsCount, v2: WordsCount): WordsCount = {
        val resSeq = (v1.count ++ v2.count)
          .groupBy(x => x.word)
          .map(x => Count (x._1, x._2.map(x => x.count).sum))
          .toSeq
        new WordsCount(resSeq)
      }
    }
  }


  def countWords(lines: Vector[String]): WordsCount = {
    val converter = (x: String) => new Task3.WordsCount(x.split(" ").map(word => new Count(word, 1)))
    Await.result(mapReduce[String, WordsCount](lines)(converter), 1.seconds)
  }




}
