

import scala.annotation.tailrec
import scala.util.Random

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

    def findSumFunctional(items: List[Int], sumValue: Int) = {
        items.zipWithIndex
          .map(x => (x, sumValue - x._1))
          .map(x => (x._1._2, items.indexWhere(z => z == x._2)))
          .filter(x => x._2 != -1 && x._1 != x._2)
          .sortBy(x => x._2)
          .union(List((-1,-1))) //bound must be positive, argument exception
          .head;
    }
    def findSumFunctional2(items: List[Int], sumValue: Int) = {
        items; //переделать с tails???
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
    @tailrec
    def tailRecRecursion(items: List[Int], index: Int = 1, mult: Int = 1): Int = {
        if (index > items.size)
            return mult
        if (items(items.size - index) % 2 == 0) {
            tailRecRecursion(items, index + 1, mult * items(items.size - index) + (items.size - index + 1))
        } else {
            tailRecRecursion(items, index + 1, mult * -1 * items(items.size - index) + (items.size - index + 1))
        }
    }

    /**
     * Задание №3
     * Реализуйте алгоритм бинарного поиска, который соответсвует всем правилам функционального программирования.
     * Необходимо возвращать индекс соответствующего элемента в массиве
     * Если ответ найден, то возвращается Some(index), если нет, то None
     */

    final case class ListData(value: Int, index: Int) {
        require(index >= 0)
    }
    def functionalBinarySearch(items: List[Int], value: Int): Option[Int] = {
        val itemsData = items.zipWithIndex.map(item => ListData(item._1, item._2))
        functionalBinarySearchHelper(itemsData, value)
    }

    @tailrec
    private def functionalBinarySearchHelper(items: List[ListData], value: Int): Option[Int] = {
        if (items.isEmpty)
            return None
        val mid = items.size / 2;
        if (items(mid).value < value) {
            functionalBinarySearchHelper(items.slice(mid, items.size), value)
        }
        else if (items(mid).value > value) {
            functionalBinarySearchHelper(items.slice(0, mid), value)
        }
        else {
            Some(items(mid).index)
        }
    }

    /**
     * Задание №4
     * Реализуйте функцию, которая генерирует список заданной длинны c именами.
     * Функция должна соответствовать всем правилам функционального программирования.
     *
     * Именем является строка, не содержащая иных символов, кроме буквенных, а также начинающаяся с заглавной буквы.
     */

    def generateNames(namesСount: Int): List[String] = {
        if (namesСount < 0) throw new Throwable("Invalid namesCount")
        (1 to namesСount).toList.map(_ => this.generateName())
    }

    private def generateName(): String = {
        Iterator.continually(Random.nextPrintableChar())
          .take(25)
          .filter(_.isLetter)
          .mkString
          .toLowerCase
          .capitalize
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
        def findPhoneNumberSafe(num: String): Either[Unit,String] = {
            val phone = unsafePhoneService.findPhoneNumber(num);
            phone match {
                case null => Left();
                case _ => Right(phone);
            }
        }

        def addPhoneToBaseSafe(phone: String): Either[String, Unit] = {
            try {
                unsafePhoneService.addPhoneToBase(phone);
                Right()
            } catch {
                case ex: InternalError => Left(ex.getMessage)
            }
        }

        def deletePhone(phone: String): Unit = {
            unsafePhoneService.deletePhone(phone)
        }
    }

    class ChangePhoneServiceSafe(phoneServiceSafety: PhoneServiceSafety) extends ChangePhoneService {
        override def changePhone(oldPhone: String, newPhone: String): String = {
            val oldPhoneRecord = phoneServiceSafety.findPhoneNumberSafe(oldPhone)
            if (oldPhoneRecord.isRight) {
                phoneServiceSafety.deletePhone(oldPhoneRecord.right.get)
            }
            val addResult = phoneServiceSafety.addPhoneToBaseSafe(newPhone)
            if (addResult.isRight)
                "ok"
            else
                addResult.left.get
        }
    }
}
