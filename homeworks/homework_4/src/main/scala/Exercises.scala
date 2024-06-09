import scala.annotation.tailrec
import scala.util.{Random, Try, Success, Failure}

object Exercises {

    def findSumImperative(items: List[Int], sumValue: Int): (Int, Int) = {
        var result: (Int, Int) = (-1, -1)
        for (i <- 0 until items.length) {
            for (j <- items.indices) {
                if (items(i) + items(j) == sumValue && i != j) {
                    result = (i, j)
                }
            }
        }
        result
    }

    def findSumFunctional(items: List[Int], sumValue: Int) = {
      items.indices.flatMap { x =>
        items.indices.filter { y =>
          items(x) + items(y) == sumValue && x != y
        }.map(j => (x, j))
      }.lastOption.getOrElse((-1, -1));
    }



    def simpleRecursion(items: List[Int], index: Int = 1): Int = {
        items match {
            case head :: tail =>
                if (head % 2 == 0) {
                    head * simpleRecursion(tail, index + 1) + index
                } else {
                    -1 * head * simpleRecursion(tail, index + 1) + index
                }
            case _ => 1
        }
    }

    def tailRecRecursion(items: List[Int]): Int = {
      @tailrec
      def rec(items: List[Int], index: Int = 1, sum: Int): Int = {
        items match {
          case head :: tail =>
            if (head % 2 == 0) {
              rec(tail, index - 1, index + (head * sum))
            } else {
              rec(tail, index - 1, index - (head * sum))
            }
          case _ => sum
        }
      }

      rec(items.reverse, items.length, 1)
    }

    /**
     * Задание №3
     * Реализуйте алгоритм бинарного поиска, который соответсвует всем правилам функционального программирования.
     * Необходимо возвращать индекс соответствующего элемента в массиве
     * Если ответ найден, то возвращается Some(index), если нет, то None
     */

    def functionalBinarySearch(items: List[Int], value: Int): Option[Int] = {
      @tailrec
      def binarySearch(l: Int, r: Int): Option[Int] = {
        Some((l + r) / 2).filter(_ => l <= r) match {
          case None => None
          case Some(m) =>
            if (items(m) == value)
              Some(m)
            else if (items(m) < value)
              binarySearch(m + 1, r)
            else
              binarySearch(l, m - 1)
        }
      }

      binarySearch(0, items.length - 1)
    }


    def generateNames(count: Int): List[String] = {
        if (count < 0)
          throw new Throwable("Invalid namesCount")
        else 
          List.tabulate(count) { _ =>
            Random.alphanumeric.filter(_.isLetter).
              take(4 + Random.nextInt(15)).
              mkString.
              toLowerCase.capitalize
          }
    }

}

object SideEffectExercise {
    import Utils._

    class SimpleChangePhoneService(phoneService: SimplePhoneService) extends ChangePhoneService {
        override def changePhone(oldPhone: String, newPhone: String): String = {
            val oldPhoneRecord = phoneService.findPhoneNumber(oldPhone)
            if (oldPhoneRecord != null) {
                phoneService.deletePhone(oldPhoneRecord)
            }
            phoneService.addPhoneToBase(newPhone)
            "ok"
        }
    }


    class PhoneServiceSafety(unsafePhoneService: SimplePhoneService) {
        def findPhoneNumberSafe(num: String): Option[String] = {
          Option(unsafePhoneService.findPhoneNumber(num))
        }

        def addPhoneToBaseSafe(phone: String): Either[String, Unit] = {
          Try(unsafePhoneService.addPhoneToBase(phone)) match {
            case Success(ok) => Right(ok)
            case Failure(exception) => Left(exception.getMessage)
          }
        }

        def deletePhone(phone: String): Option[Unit] = {
          Option(findPhoneNumberSafe(phone).map(unsafePhoneService.deletePhone))
        }
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
