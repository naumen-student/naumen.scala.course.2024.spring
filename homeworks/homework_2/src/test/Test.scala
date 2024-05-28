import Exercises.Vector2D
import utest._

object Test extends TestSuite {
  val tests = Tests {
    'test_divBy3Or7 - {
      assert(Exercises.divBy3Or7(1, 3) == Seq(3))
      assert(Exercises.divBy3Or7(5, 9) == Seq(6, 7, 9))
      assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
    }

    /**
     * Тесты для функции [[Exercises.sumOfDivBy3Or5]]
     */
    'sumOfDivBy3Or5 - {
      assert(Exercises.sumOfDivBy3Or5(1, 3) == 3) // 3 = 3
      assert(Exercises.sumOfDivBy3Or5(0, 3) == 3) // 3 = 3
      assert(Exercises.sumOfDivBy3Or5(-3, 3) == 0) // -3 + 3 = 0
      assert(Exercises.sumOfDivBy3Or5(-3, -1) == -3) // -3 + 3 = 0
      assert(Exercises.sumOfDivBy3Or5(1, 5) == 8) // 3 + 5 = 8
      assert(Exercises.sumOfDivBy3Or5(6, 20) == 90) // 6 + 9 + 10 + 12 + 15 + 18 + 20 = 90
    }

    /**
     * Тесты для функции [[Exercises.primeFactor]]
     */
    'primeFactor - {
      assert(Exercises.primeFactor(-2) == Seq())
      assert(Exercises.primeFactor(-1) == Seq())
      assert(Exercises.primeFactor(0) == Seq())
      assert(Exercises.primeFactor(1) == Seq())
      assert(Exercises.primeFactor(2) == Seq(2))
      assert(Exercises.primeFactor(240) == Seq(2, 3, 5))
      assert(Exercises.primeFactor(98) == Seq(2, 7))
    }

    /**
     * Тесты для функции [[Exercises.sumScalars]]
     */
    'sumScalars - {
      // Коллинеарные векторы
      assert(Exercises.sumScalars(Vector2D(1, 2), Vector2D(2, 4), Vector2D(-1, -2), Vector2D(-2, -4)) == 20)
      // Ортогональные векторы
      assert(Exercises.sumScalars(Vector2D(0, 5), Vector2D(5, 0), Vector2D(-5, 0), Vector2D(0, -5)) == 0)
      // Произвольные векторы
      assert(Exercises.sumScalars(Vector2D(3, 4), Vector2D(2, -1), Vector2D(-5, 9), Vector2D(4, 3)) == 9)
    }

    /**
     * Тесты для функции [[Exercises.sumCosines]]
     */
    'sumCosines - {
      // Векторы одинакового направления
      assert(Exercises.sumCosines(Vector2D(1, 2), Vector2D(2, 4), Vector2D(-1, -2), Vector2D(-2, -4)) == 2)
      // Ортогональные векторы
      assert(Exercises.sumCosines(Vector2D(0, 5), Vector2D(5, 0), Vector2D(-5, 0), Vector2D(0, -5)) == 0)
      // Произвольные векторы
      val cosSum = Exercises.sumCosines(Vector2D(3, 4), Vector2D(2, -1), Vector2D(-5, 9), Vector2D(4, 3))
      assert(Math.abs(cosSum - 0.3148654589300002) < 0.000001)
    }

    /**
     * Тесты для функции [[Exercises.sortByHeavyweight]]
     */
    'sortByHeavyweight - {
      assert(Exercises.sortByHeavyweight(Map()) == Seq())
      assert(Exercises.sortByHeavyweight(Map("Aluminum" -> (3, 2.6889))) == Seq("Aluminum"))

      val threeBalls = Map("Tungsten" -> (2, 19.35), "Graphite" -> (12, 2.1), "Aluminum" -> (3, 2.6889))
      val threeBallsExpected = Seq("Aluminum", "Tungsten", "Graphite")
      assert(Exercises.sortByHeavyweight(threeBalls) == threeBallsExpected)
    }
  }
}