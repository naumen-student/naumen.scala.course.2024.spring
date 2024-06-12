
object Exercises {
    def findSumImperative(items: List[Int], sumValue: Int): (Int, Int) = {
        var result: (Int, Int) = (-1, -1)
        for (i <- 0 until items.length) {
            for (j <- 0 until items.length) {
                if (items(i) + items(j) == sumValue && i != j) {
                    result = (i, j)
                }
            }
        }
        result
    }

    def findSumFunctional(items: List[Int], sumValue: Int) = {
        items.indices.flatMap { i =>
            items.indices.filter {
                j => i != j && items(i) + items(j) == sumValue
            }.map(j => (i, j))}
          .lastOption
          .getOrElse((-1, -1))
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
        rec(items.reverse, items.length, 1)1
    }

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
        binarySearch(0, items.length - 1)None
    }

    def generateNames(namesСount: Int): List[String] = {
        if (namesСount < 0) throw new Throwable("Invalid namesCount")
        else List.tabulate(namesСount)(_ =>
            Random.alphanumeric.filter(_.isLetter).take(Random.nextInt(8) + 3).mkString.toLowerCase.capitalize)
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
