@@ -1,3 +1,5 @@
import scala.annotation.tailrec
import scala.util.{Random,Try,Success,Failure}

object Exercises {

  @@ -23,7 +25,9 @@

object Exercises {
  }

  def findSumFunctional(items: List[Int], sumValue: Int) = {
    items.zipWithIndex.flatMap(
      first => items.zipWithIndex.filter(second => first._1 + second._1 == sumValue && first._2 != second._2).map(second => (first._2, second._2))
    ).lastOption.getOrElse((-1, -1))
  }


  @@ -49,7 +53,20 @@

object Exercises {
  }

  def tailRecRecursion(items: List[Int]): Int = {
    @tailrec
    def rec(items: List[Int], index: Int, result: Int): Int =
      items match {
        case head :: tail =>
          if (head % 2 == 0) {
            rec(tail, index - 1, index + head * result)
          } else {
            rec(tail, index - 1, index - head * result)
          }
        case _ => result
      }

    rec(items.reverse, items.length, 1)
  }

  /**
  @@ -60,7 +77,18 @@ object Exercises {
   */

  def functionalBinarySearch(items: List[Int], value: Int): Option[Int] = {
    @tailrec
    def binarySearch(l: Int, r: Int): Option[Int] =
      Some((l + r) / 2).filter(_ => l <= r) match {
        case None => None
        case Some(m) =>
          if (items(m) == value) Some(m)
          else if (items(m) > value) binarySearch(l, m - 1)
          else binarySearch(m + 1, r)
      }

    binarySearch(0, items.length - 1)
  }


    def generateNames(namesCount: Int): List[String] = {
      if (namesCount < 0) throw new Throwable("Invalid namesCount")
      else List.tabulate(namesCount)(_ =>
        Random.alphanumeric.filter(_.isLetter).take(10).mkString.toLowerCase.capitalize
      )
    }

  }
  @@ -111,14 +141,25 @@

object SideEffectExercise {


    class PhoneServiceSafety(unsafePhoneService: SimplePhoneService) {
      def findPhoneNumberSafe(num: String): Option[String] = Option(unsafePhoneService.findPhoneNumber(num))

      def addPhoneToBaseSafe(phone: String): Either[String, Unit] = {
        Try(unsafePhoneService.addPhoneToBase(phone)) match {
          case Success(ok) => Right(ok)
          case Failure(exception) => Left(exception.getMessage)
        }
      }

      def deletePhone(phone: String): Option[Unit] = Option(findPhoneNumberSafe(phone).map(unsafePhoneService.deletePhone))
    }

    class ChangePhoneServiceSafe(phoneServiceSafety: PhoneServiceSafety) extends ChangePhoneService {
      override def changePhone(oldPhone: String, newPhone: String): String = {
        phoneServiceSafety.findPhoneNumberSafe(oldPhone).foreach(phoneServiceSafety.deletePhone)
        phoneServiceSafety.addPhoneToBaseSafe(newPhone) match {
          case Right(_) => "ok"
          case Left(message) => message
        }
      }
    }
  }