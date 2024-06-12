object Exercises {

  def findSumImperative(items: List[Int], sumValue: Int): (Int, Int) = {
    var result: (Int, Int) = (-1, -1)
    for (i <- 0 until items.length) {
      for (j <- i + 1 until items.length) {
        if (items(i) + items(j) == sumValue) {
          result = (i, j)
        }
      }
    }
    result
  }

  def findSumFunctional(items: List[Int], sumValue: Int): (Int, Int) = {
    items.indices.flatMap { i =>
      items.indices.collect {
        case j if i < j && items(i) + items(j) == sumValue => (i, j)
      }
    }.lastOption.getOrElse((-1, -1))
  }

  def simpleRecursion(items: List[Int], index: Int = 1): Int = {
    items match {
      case head :: tail => head * index + simpleRecursion(tail, index + 1)
      case Nil => 0
    }
  }

  @scala.annotation.tailrec
  def tailRecRecursion(items: List[Int], index: Int = items.length, result: Int = 1): Int = {
    items match {
      case head :: tail => tailRecRecursion(tail, index - 1, if (head % 2 == 0) index + head * result else index - head * result)
      case Nil => result
    }
  }

  @scala.annotation.tailrec
  def functionalBinarySearch(items: List[Int], value: Int, l: Int = 0, r: Int = items.length - 1): Option[Int] = {
    if (l > r) None
    else {
      val mid = l + (r - l) / 2
      items(mid) match {
        case v if v == value => Some(mid)
        case v if v < value => functionalBinarySearch(items, value, mid + 1, r)
        case _ => functionalBinarySearch(items, value, l, mid - 1)
      }
    }
  }

  def generateNames(namesCount: Int): List[String] = {
    if (namesCount < 0) throw new IllegalArgumentException("Invalid namesCount")
    List.tabulate(namesCount)(_ => scala.util.Random.alphanumeric.filter(_.isLetter).take(scala.util.Random.nextInt(8) + 3).mkString.capitalize)
  }
}

object SideEffectExercise {
  import scala.util.{Try, Success, Failure}

  class PhoneServiceSafety(unsafePhoneService: SimplePhoneService) {
    def findPhoneNumberSafe(num: String): Option[String] = Option(unsafePhoneService.findPhoneNumber(num))

    def addPhoneToBaseSafe(phone: String): Either[String, Unit] = {
      Try(unsafePhoneService.addPhoneToBase(phone)) match {
        case Success(_) => Right(())
        case Failure(exception) => Left(exception.getMessage)
      }
    }

    def deletePhone(phone: String): Option[Unit] = findPhoneNumberSafe(phone).map(_ => unsafePhoneService.deletePhone(phone))
  }

  class ChangePhoneServiceSafe(phoneServiceSafety: PhoneServiceSafety) {
    def changePhone(oldPhone: String, newPhone: String): String = {
      phoneServiceSafety.findPhoneNumberSafe(oldPhone).foreach(_ => phoneServiceSafety.deletePhone(oldPhone))
      phoneServiceSafety.addPhoneToBaseSafe(newPhone) match {
        case Right(_) => "ok"
        case Left(message) => message
      }
    }
  }
}