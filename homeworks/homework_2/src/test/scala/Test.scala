import Exercises.{Vector2D, cosBetween, sumByFunc, sumCosines, sumScalars}
import utest._

object Test extends TestSuite {

    val tests: Tests = Tests {
        'test_divBy3Or7 - {
            assert(Exercises.divBy3Or7(1, 3) == Seq(3))
            assert(Exercises.divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }

        'test_sumOfDivBy3Or5 - {
            assert(Exercises.sumOfDivBy3Or5(1, 3) == 3)
            assert(Exercises.sumOfDivBy3Or5(5, 9) == 20)
            assert(Exercises.sumOfDivBy3Or5(0, 100) == 2418)
            assert(Exercises.sumOfDivBy3Or5(0, 0) == 0)
        }

        'test_primeFactor - {
            assert(Exercises.primeFactor(80) == Seq(2, 5))
            assert(Exercises.primeFactor(98) == Seq(2, 7))
            assert(Exercises.primeFactor(100) == Seq(2, 5))
            assert(Exercises.primeFactor(7) == Seq(7))
            assert(Exercises.primeFactor(1) == Seq())
            assert(Exercises.primeFactor(1000000000) == Seq(2, 5))
            assert(Exercises.primeFactor(832) == Seq(2, 13))
        }

        'test_sumScalars - {
            assert(sumScalars(Vector2D(1, 2), Vector2D(3, 4), Vector2D(5, 6), Vector2D(7, 8)) == 94)
            assert(sumScalars(Vector2D(0, 0), Vector2D(1, 8), Vector2D(1000, 200), Vector2D(4, 8)) == 5600)
            assert(sumScalars(Vector2D(-5, 4), Vector2D(3, 3), Vector2D(1, 6), Vector2D(-5, -8)) == -56)
            assert(sumScalars(Vector2D(15, 0), Vector2D(12, -12), Vector2D(-15, 0), Vector2D(-12, 12)) == 360)
        }

        'test_sumCosines - {
            assert(sumCosines(Vector2D(1, 2), Vector2D(3, 4), Vector2D(5, 6), Vector2D(7, 8)) == 1.9835797185673898)
            assert(sumCosines(Vector2D(-5, 4), Vector2D(3, 3), Vector2D(1, 6), Vector2D(-5, -8)) == -1.0340231895748642)
            assert(sumCosines(Vector2D(15, 0), Vector2D(12, -12), Vector2D(-15, 0), Vector2D(-12, 12)) == 1.4142135623730951)
        }

        'test_sortByHeavyweight - {
            assert(Exercises.sortByHeavyweight() == Seq(
                "Tin", "Platinum", "Nickel", "Aluminum", "Titanium", "Lead", "Sodium", "Uranium",
                "Gold", "Tungsten", "Zirconium", "Chrome", "Iron", "Copper", "Silver", "Plutonium",
                "Cobalt", "Cesium", "Calcium", "Lithium", "Magnesium", "Potassium", "Graphite"
            ))
        }
    }
}
