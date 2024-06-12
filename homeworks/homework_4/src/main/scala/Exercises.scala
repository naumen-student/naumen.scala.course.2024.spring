
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
    def findPair(index: Int): (Int, Int) = {
        if (index >= items.length) (-1, -1)
        else {
            val remaining = items.drop(index + 1)
            val indexOfPair = remaining.indexWhere(num => num + items(index) == sumValue)
            if (indexOfPair != -1) (index, index + indexOfPair + 1)
            else findPair(index + 1)
        }
    }

    findPair(0)
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

      import scala.annotation.tailrec@tailrec
    
def tailRecRecursion(items: List[Int], acc: Int = 1, index: Int = 1): Int = {
    items match {
        case head :: tail =>
            val newValue = if (head % 2 == 0) head * acc + index else -1 * head * acc + index
            tailRecRecursion(tail, newValue, index + 1)
        
        case _ => acc
    }
}

  

    /**
     * Задание №3
     * Реализуйте алгоритм бинарного поиска, который соответсвует всем правилам функционального программирования.
     * Необходимо возвращать индекс соответствующего элемента в массиве
     * Если ответ найден, то возвращается Some(index), если нет, то None
     */

    def functionalBinarySearch(items: List[Int], value: Int): Option[Int] = {
    @scala.annotation.tailrec
    def binarySearch(start: Int, end: Int): Option[Int] = {
        if (start > end) {
            None
        } else {
            val mid = start + (end - start) / 2
            items(mid) match {
                case midValue if midValue == value => Some(mid)
                case midValue if midValue < value => binarySearch(mid + 1, end)
                case _ => binarySearch(start, mid - 1)
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

    import scala.util.Random

def generateNames(namesCount: Int): List[String] = {
  if (namesCount < 0) throw new IllegalArgumentException("Invalid namesCount")

  def generateName: String = {
    val alphabet = 'A' to 'Z'
    val nameLength = Random.nextInt(5) + 1
    val name = (1 to nameLength).map(_ => alphabet(Random.nextInt(alphabet.length))).mkString
    name.head + name.tail.toLowerCase
  }

  (1 to namesCount).map(_ => generateName).toList
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
        def findPhoneNumberSafe(num: String): Option[String] = ???

        def addPhoneToBaseSafe(phone: String) = ???

        def deletePhone(phone: String) = ???
    }

    class ChangePhoneServiceSafe(phoneServiceSafety: PhoneServiceSafety) extends ChangePhoneService {
        override def changePhone(oldPhone: String, newPhone: String): String = ???
    }
}


