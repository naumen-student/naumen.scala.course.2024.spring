import scala.annotation.tailrec
import scala.util.{Failure, Random, Success, Try}

object Exercises {

    /**
     * Задание №1
     * Дана императивная функция findSumImperative.
     * Напишите ее аналог (findSumFunctional) в функциональном стиле.
     *
     * ПОДСКАЗКА
     * Стоит воспользоваться методами, которые предоставляет объект List или рекурсией.
     * Страница с полезностями List: https://alvinalexander.com/scala/list-class-methods-examples-syntax/
     */
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

    def findSumFunctional(items: List[Int], sumValue: Int): (Int, Int) = {
        items.zipWithIndex
          .flatMap { case (item1, i) =>
            items.zipWithIndex.collect { case (item2, j) if i != j && item1 + item2 == sumValue => (i, j) }
          }
          .lastOption
          .getOrElse((-1, -1))
    }


    /**
     * Задание №2
     *
     * Дана рекурсивная функция simpleRecursion.
     * Перепишите ее так, чтобы получилась хвостовая рекурсивная функция.
     *
     * Для прохождения теста на большое количество элементов в списке
     * используйте анотацию @tailrec к вашей функции.
     */
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
        def loop(items: List[Int], index: Int, acc: Int): Int =
            items match {
                case head :: tail =>
                    if (head % 2 == 0) {
                        loop(tail, index - 1, acc * head + index)
                    } else {
                        loop(tail, index - 1, -1 * acc * head + index)
                    }
                case _ => acc
            }

        loop(items.reverse, items.length, 1)
    }

    /**
     * Задание №3
     * Реализуйте алгоритм бинарного поиска, который соответсвует всем правилам функционального программирования.
     * Необходимо возвращать индекс соответствующего элемента в массиве
     * Если ответ найден, то возвращается Some(index), если нет, то None
     */

    def functionalBinarySearch(items: List[Int], value: Int): Option[Int] = {
        @tailrec
        def binarySearch(low: Int, high: Int): Option[Int] = {
            if (low > high) {
                None
            } else {
                val mid = low + (high - low) / 2
                items(mid) match {
                    case midVal if midVal == value => Some(mid)
                    case midVal if midVal > value  => binarySearch(low, mid - 1)
                    case midVal if midVal < value  => binarySearch(mid + 1, high)
                }
            }
        }

        binarySearch(0, items.length - 1)
    }

    /**
     * Задание №4
     * Реализуйте функцию, которая генерирует список заданной длинны c именами.
     * Функция должна соответствовать всем правилам функционального программирования.
     *
     * Именем является строка, не содержащая иных символов, кроме буквенных, а также начинающаяся с заглавной буквы.
     */

    def generateNames(namesCount: Int): List[String] = {
        if (namesCount < 0) throw new IllegalArgumentException("Invalid namesCount")

        def randomChar(isUpper: Boolean = false): Char = {
            val chars = if (isUpper) ('A' to 'Z') else ('a' to 'z')
            chars(Random.nextInt(chars.size))
        }

        def randomName(length: Int = 5): String = {
            val firstChar = randomChar(isUpper = true)
            val restChars = (1 until length).map(_ => randomChar()).mkString
            firstChar + restChars
        }

        List.fill(namesCount)(randomName())
    }

}

/**
 * Задание №5
 *
 * Дана реализация сервиса по смене номера SimpleChangePhoneService с методом changePhone
 * Необходимо написать реализацию этого сервиса с учетом правил работы со сторонними эффектами (SideEffects).
 *
 * Для этого необходимо сначала реализовать собственный сервис работы с телефонными номерами (PhoneServiceSafety),
 * используя при этом методы из unsafePhoneService.
 * Методы должны быть безопасными, поэтому тип возвращаемых значений необходимо определить самостоятельно.
 * Рекомендуется воспользоваться стандартными типами Scala (например Option или Either).
 *
 * Затем, с использованием нового сервиса, необходимо реализовать "безопасную" версию функции changePhone.
 * Функция должна возвращать ok в случае успешного завершения или текст ошибки.
 *
 * Изменять методы внутри SimplePhoneService не разрешается.
 */

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
                case Success(_) => Right(())
                case Failure(e) => Left(e.getMessage)
            }
        }

        def deletePhoneSafe(phone: String): Either[String, Unit] = {
            Try(unsafePhoneService.deletePhone(phone)) match {
                case Success(_) => Right(())
                case Failure(e) => Left(e.getMessage)
            }
        }
    }

    class ChangePhoneServiceSafe(phoneServiceSafety: PhoneServiceSafety) extends ChangePhoneService {
        override def changePhone(oldPhone: String, newPhone: String): String = {
            phoneServiceSafety.findPhoneNumberSafe(oldPhone) match {
                case Some(oldPhoneRecord) =>
                    phoneServiceSafety.deletePhoneSafe(oldPhoneRecord) match {
                        case Right(_) =>
                            phoneServiceSafety.addPhoneToBaseSafe(newPhone) match {
                                case Right(_) => "ok"
                                case Left(error) => s"Error adding new phone: $error"
                            }
                        case Left(error) => s"Error deleting old phone: $error"
                    }
                case None => "Old phone number not found"
            }
        }
    }
}
