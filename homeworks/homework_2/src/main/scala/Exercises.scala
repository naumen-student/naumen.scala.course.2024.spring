object Exercises {

    /*ПРИМЕР*/
    /*Реализовать функцию, которая возвращает все целые числа в заданном диапазоне (от iForm до iTo), которые делятся
    на 3 или на 7.*/
    /*Реализовать юнит-тесты в src/test/scala для данной функции.*/
    def divBy3Or7(iFrom: Int, iTo: Int): Seq[Int] = {
        for {i <- iFrom to iTo
             if i % 3 == 0 || i % 7 == 0
             } yield i
    }



    /*ЗАДАНИЕ I*/
    /*Реализовать функцию, которая возвращает сумму всех целых чисел в заданном диапазоне (от iForm до iTo), которые делятся
    на 3 или на 5.*/
    /*Реализовать юнит-тесты в src/test/scala для данной функции.*/
    def sumOfDivBy3Or5(iFrom: Int, iTo: Int): Int = {
        val k = (
          for {i <- iFrom to iTo
               if i % 3 == 0 || i % 5 == 0
               } yield i)
        val sam: Int = k.sum
        sam
    }

    /*ЗАДАНИЕ II*/
    /*Реализовать функцию, которая вычисляет все различные простые множители целого числа отличные от 1.
    Число 80 раскладывается на множители 1 * 2 * 2 * 2 * 2 * 5, результат выполнения функции => Seq(2, 5).
    Число 98 можно разложить на множители 1 * 2 * 7 * 7, результат выполнения функции => Seq(2, 7).*/
    /*Реализовать юнит-тесты в src/test/scala для данной функции.*/
    def primeFactor(number: Int): Seq[Int] = {
        if (number < 2) throw new IllegalArgumentException("Число введено неверно")
        def loop(n: Int, a: Int = 2, factors: Set[Int] = Set()): Set[Int] = {
            if (a * a > n) factors+n
            else if (n % a == 0) loop(n / a, a,factors+a)
            else loop(n, a + 1, factors)
        }
        val result = loop(number).toList
        result
    }
    /*ЗАДАНИЕ IV*/
    /*Дано: коллекция металлических шариков balls, где каждый элемент представлен в виде (Name: String -> (radius: Int, density: Double).
    Здесь radius - радиус шарика [см], а density - плотность материала [г / (см^3)], из которого он изготовлен (например,
    для серебра в коллекции представлен шарик "Silver" радиуса 4 см и плотности 4.505 г / (см^3) )
    Необходимо реализовать функцию sortByHeavyweight, которая принимает коллекцию такого формата и возвращает список названий материалов шариков,
    упорядоченный в зависимости от массы шариков (первый элемент списка соответствует наиболее "лёгкому" шарику, последний - наиболее "тяжёлому").
    В качестве значения числа "Пи" можно использовать java.lang.Math.PI
    */
    /*Реализовать юнит-тесты в src/test/scala для данной функции.*/
    val balls: Map[String, (Int, Double)] =
        Map(
            "Aluminum" -> (3,   2.6889), "Tungsten" ->  (2,   19.35), "Graphite" ->  (12,  2.1),   "Iron" ->      (3,   7.874),
            "Gold" ->     (2,   19.32),  "Potassium" -> (14,  0.862), "Calcium" ->   (8,   1.55),  "Cobalt" ->    (4,   8.90),
            "Lithium" ->  (12,  0.534),  "Magnesium" -> (10,  1.738), "Copper" ->    (3,   8.96),  "Sodium" ->    (5,   0.971),
            "Nickel" ->   (2,   8.91),   "Tin" ->       (1,   7.29),  "Platinum" ->  (1,   21.45), "Plutonium" -> (3,   19.25),
            "Lead" ->     (2,   11.336), "Titanium" ->  (2,   10.50), "Silver" ->    (4,   4.505), "Uranium" ->   (2,   19.04),
            "Chrome" ->   (3,   7.18),   "Cesium" ->    (7,   1.873), "Zirconium" -> (3,   6.45)
        )

    def sortByHeavyweight(ballsArray: Map[String, (Int, Double)] = balls): Seq[String] = {
        def getWeight(elementName: String): Double = {
            if (balls.contains(elementName)) return Double.MaxValue

            val elementData = balls(elementName)
            elementData._2 * (4 / 3) * Math.PI * Math.pow(elementData._1, 2)
        }
        ballsArray.keys.toSeq.sortWith(getWeight(_) < getWeight(_))

    }
}