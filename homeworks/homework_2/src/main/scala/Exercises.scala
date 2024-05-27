import scala.collection.immutable.{ListMap, SortedMap}
import scala.collection.mutable.ListBuffer

object Exercises {

  /*ПРИМЕР*/
  @@ -7,26 +10,68 @@ object Exercises {
    def divBy3Or7(iFrom: Int, iTo: Int): Seq[Int] = {
      for {i <- iFrom to iTo
           if i % 3 == 0 || i % 7 == 0
           } yield i
    } yield i
  }



  /*ЗАДАНИЕ I*/
  /*Реализовать функцию, которая возвращает сумму всех целых чисел в заданном диапазоне (от iForm до iTo), которые делятся
  на 3 или на 5.*/
  /*Реализовать функцию, которая возвращает сумму всех целых чисел в заданном диапазоне (от iForm до iTo), которые
  делятся на 3 или на 5.*/
  /*Реализовать юнит-тесты в src/test/scala для данной функции.*/
  def sumOfDivBy3Or5(iFrom: Int, iTo: Int): Long = ???

  /**
   * Возвращает сумму всех целых чисел в заданном диапазоне (от iForm до iTo), которые
   * делятся на 3 или на 5.
   *
   * @param iFrom от
   * @param iTo   до
   * @return сумма чисел
   */
  def sumOfDivBy3Or5(iFrom: Int, iTo: Int): Long = {
    var result = 0L;
    for {i <- iFrom to iTo if i % 3 == 0 || i % 5 == 0} {
      result += i
    }
    result
  }


  /*ЗАДАНИЕ II*/
  /*Реализовать функцию, которая вычисляет все различные простые множители целого числа отличные от 1.
  Число 80 раскладывается на множители 1 * 2 * 2 * 2 * 2 * 5, результат выполнения функции => Seq(2, 5).
  Число 98 можно разложить на множители 1 * 2 * 7 * 7, результат выполнения функции => Seq(2, 7).*/
  /*Реализовать юнит-тесты в src/test/scala для данной функции.*/
  def primeFactor(number: Int): Seq[Int] = ???

  /**
   * Вычисляет все различные простые множители целого числа отличные от 1.
   *
   * @example Число 80 раскладывается на множители 1 * 2 * 2 * 2 * 2 * 5, результат выполнения функции => Seq(2, 5).
   * @example 98 можно разложить на множители 1 * 2 * 7 * 7, результат выполнения функции => Seq(2, 7).
   * @param number Число, для которого находятся множители
   * @return Коллекция простых множителей
   */
  def primeFactor(number: Int): Seq[Int] = {
    val result = ListBuffer[Int]()

    var i = 2
    var currentNumber = number

    while (i <= currentNumber) {
      if (currentNumber % i == 0) {
        currentNumber /= i
        result += i

        while (currentNumber % i == 0) {
          currentNumber /= i
        }
      }

      i += 1
    }

    result
  }


  /*ЗАДАНИЕ III*/
  @@ -36,20 +81,75 @@ object Exercises {
    Функция sumScalars должна вычислять сумму скалярных произведений для пар векторов scalar(leftVec0, leftVec1) + scalar(rightVec0, rightVec1).
      Функция sumCosines должна вычислять сумму косинусов углов между парами векторов cosBetween(leftVec0, leftVec1) + cosBetween(rightVec0, rightVec1).*/
    /*Реализовать юнит-тесты в src/test/scala для функций sumScalars и sumCosines*/

    /**
     * Двумерный вектор
     * @param x Координата X
     * @param y Координата Y
     */
    case class Vector2D(x: Double, y: Double)

    /**
     * Вычисляет модуль вектора
     * @param vec Вектор
     * @return
     */
    def abs(vec: Vector2D): Double = java.lang.Math.sqrt(vec.x * vec.x + vec.y * vec.y)

    /**
     * Вычисляет скалярное произведение
     * @param vec0 Вектор 0
     * @param vec1 Вектор 1
     * @return
     */
    def scalar(vec0: Vector2D, vec1: Vector2D): Double = vec0.x * vec1.x + vec0.y * vec1.y

    /**
     * Вычисляет косинус между двумя векторами
     * @param vec0 Вектор 0
     * @param vec1 Вектор 1
     * @return
     */
    def cosBetween(vec0: Vector2D, vec1: Vector2D): Double = scalar(vec0, vec1) / abs(vec0) / abs(vec1)
    //def sumByFunc(leftVec0: Vector2D, leftVec1: Vector2D, ???, rightVec0: Vector2D, rightVec1: Vector2D) = ???
    /*

    /**
     * Тип функции для вычисления какого-либо значения из пары векторов
     */
    private type PairVectorFunc = (Vector2D, Vector2D) => Double

    /**
     * Вычисляет значения по функции для пар векторов и возвращает сумму этих значений
     * @param leftVec0 Вектор 0 левой пары
     * @param leftVec1 Вектор 1 левой пары
     * @param func Функция для вычисления какого-либо значения из пары векторов
     * @param rightVec0 Вектор 0 правой пары
     * @param rightVec1 Вектор 1 правой пары
     * @return Сумма исполненных функций
     */
    def sumByFunc(leftVec0: Vector2D, leftVec1: Vector2D, func: PairVectorFunc, rightVec0: Vector2D, rightVec1: Vector2D): Double =
        func(leftVec0, leftVec1) + func(rightVec0, rightVec1)

    /**
     * Вычисляет сумму скалярных произведений для пар векторов
     * @param leftVec0 Вектор 0 левой пары
     * @param leftVec1 Вектор 1 левой пары
     * @param rightVec0 Вектор 0 правой пары
     * @param rightVec1 Вектор 1 правой пары
     * @return Сумма скалярных произведений
     */
    def sumScalars(leftVec0: Vector2D, leftVec1: Vector2D, rightVec0: Vector2D, rightVec1: Vector2D): Double =
        sumByFunc(leftVec0, leftVec1, scalar, rightVec0, rightVec1)
    */
    /*

    /**
     * Вычисляет сумму косинусов углов между парами векторов
     * @param leftVec0 Вектор 0 левой пары
     * @param leftVec1 Вектор 1 левой пары
     * @param rightVec0 Вектор 0 правой пары
     * @param rightVec1 Вектор 1 правой пары
     * @return Сумма косинусов углов
     */
    def sumCosines(leftVec0: Vector2D, leftVec1: Vector2D, rightVec0: Vector2D, rightVec1: Vector2D): Double =
        sumByFunc(leftVec0, leftVec1, cosBetween, rightVec0, rightVec1)
    */



    /*ЗАДАНИЕ IV*/
    @@ -62,15 +162,37 @@ object Exercises {
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

      def sortByHeavyweight(ballsArray: Map[String, (Int, Double)] = balls): Seq[String] = ???
      Map(
        "Aluminum" -> (3, 2.6889), "Tungsten" -> (2, 19.35), "Graphite" -> (12, 2.1), "Iron" -> (3, 7.874),
        "Gold" -> (2, 19.32), "Potassium" -> (14, 0.862), "Calcium" -> (8, 1.55), "Cobalt" -> (4, 8.90),
        "Lithium" -> (12, 0.534), "Magnesium" -> (10, 1.738), "Copper" -> (3, 8.96), "Sodium" -> (5, 0.971),
        "Nickel" -> (2, 8.91), "Tin" -> (1, 7.29), "Platinum" -> (1, 21.45), "Plutonium" -> (3, 19.25),
        "Lead" -> (2, 11.336), "Titanium" -> (2, 10.50), "Silver" -> (4, 4.505), "Uranium" -> (2, 19.04),
        "Chrome" -> (3, 7.18), "Cesium" -> (7, 1.873), "Zirconium" -> (3, 6.45)
      )

      /**
       * Вычисляет вес шарика
       * @param radius Радиус в см
       * @param density Плотность в г / (см^3^)
       * @return Вес в граммах
       */
      private def calculateWeight(radius: Int, density: Double) : Double = {
        val volume = (4/3) * Math.PI * Math.pow(radius, 3) // в см^3
        volume * density
      }

      /**
       * Сортирует переданные шарики по весу в порядке возрастания и возвращает последовательность названий
       * @param ballsArray Map шариков название -> кортеж(радиус в см, плотность в г / (см^3^))
       * @return Отсортированные названия
       */
      def sortByHeavyweight(ballsArray: Map[String, (Int, Double)] = balls): Seq[String] = {
        ballsArray
          .map(pair => (pair._1, calculateWeight(pair._2._1, pair._2._2))) // преобразуем значения в веса
          .toSeq // делаем последовательность из (название, вес) // (название, вес) => (вес, название)
          .sortBy(_._2) // сортируем по весу
          .map(pair => pair._1) // оставляем только названия
      }

    }