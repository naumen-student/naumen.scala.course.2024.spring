import Exercises.Vector2D
import utest._

object Test extends TestSuite{

    val tests = Tests{
        'test_divBy3Or7 - {
            assert(Exercises.divBy3Or7(1, 3) == Seq(3))
            assert(Exercises.divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }

        'sumOfDivBy3Or5 - {
            assert(Exercises.sumOfDivBy3Or5(1, 5) == 8)
            assert(Exercises.sumOfDivBy3Or5(3, 10) == 33)
            assert(Exercises.sumOfDivBy3Or5(10, 120) == 3397)
            assert(Exercises.sumOfDivBy3Or5(-5, 0) == -8)
            assert(Exercises.sumOfDivBy3Or5(-100, -1) == -2418)
            assert(Exercises.sumOfDivBy3Or5(0, 0) == 0)
            assert(Exercises.sumOfDivBy3Or5(-1000, 1000) == 0)
        }

        'primeFactor - {
            assert(Exercises.primeFactor(98) == Seq(2, 7))
            assert(Exercises.primeFactor(80) == Seq(2, 5))
            assert(Exercises.primeFactor(130) == Seq(2, 5, 13))
            assert(Exercises.primeFactor(3003) == Seq(3, 7, 11, 13))
            assert(Exercises.primeFactor(10000000) == Seq(2, 5))
        }

        'sumScalars - {
            assert(Exercises.sumScalars(Vector2D(1, 3), Vector2D(4, 12), Vector2D(5, 9), Vector2D(0, 6)) == 94)
            assert(Exercises.sumScalars(Vector2D(2, 8), Vector2D(15, 23), Vector2D(-4, -10), Vector2D(-15, -11)) == 384)
            assert(Exercises.sumScalars(Vector2D(0, 0), Vector2D(0, 0), Vector2D(0, 0), Vector2D(0, 0)) == 0)
            assert(Exercises.sumScalars(Vector2D(-2.25, 5.63), Vector2D(9.13, -10.9), Vector2D(4.903, 0.49), Vector2D(-8.93, 19.6123)) - 116.083263 < 0.000001)
        }

        'sumCosines - {
            assert(math.abs(Exercises.sumCosines(Vector2D(1, 4), Vector2D(8, 3), Vector2D(-10, 0), Vector2D(-19, -4)) - 1.54628274079) < 0.0000001)
            assert(Exercises.sumCosines(Vector2D(1, 0), Vector2D(0, 1), Vector2D(1, 0), Vector2D(0, 1)) == 0)
        }

        'sortByHeavyweight - {
            assert(Exercises.sortByHeavyweight(Map[String, (Int, Double)] (
                "Aluminum" -> (3, 2.6889), "Tungsten" -> (2, 19.35), "Graphite" -> (12, 2.1), "Iron" -> (3, 7.874),
                "Gold" -> (2, 19.32), "Potassium" -> (14, 0.862), "Calcium" -> (8, 1.55), "Cobalt" -> (4, 8.90),
                "Lithium" -> (12, 0.534), "Magnesium" -> (10, 1.738), "Copper" -> (3, 8.96), "Sodium" -> (5, 0.971),
                "Nickel" -> (2, 8.91), "Tin" -> (1, 7.29), "Platinum" -> (1, 21.45), "Plutonium" -> (3, 19.25),
                "Lead" -> (2, 11.336), "Titanium" -> (2, 10.50), "Silver" -> (4, 4.505), "Uranium" -> (2, 19.04),
                "Chrome" -> (3, 7.18), "Cesium" -> (7, 1.873), "Zirconium" -> (3, 6.45)))
              == List (
                "Tin", "Platinum", "Nickel", "Aluminum", "Titanium", "Lead", "Sodium", "Uranium", "Gold",
                "Tungsten", "Zirconium", "Chrome", "Iron", "Copper", "Silver", "Plutonium", "Cobalt", "Cesium",
                "Calcium", "Lithium", "Magnesium", "Potassium", "Graphite"))
        }
    }
}
