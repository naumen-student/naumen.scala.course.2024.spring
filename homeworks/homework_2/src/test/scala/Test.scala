import Exercises.{Vector2D, sumCosines, sumScalars}
import utest._

object Test extends TestSuite{

    val tests = Tests{
        'test_divBy3Or7 - {
            assert(Exercises.divBy3Or7(1, 3) == Seq(3))
            assert(Exercises.divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }

        'test_sumOfDivBy3Or5 - {
            assert(Exercises.sumOfDivBy3Or5(0, 1) == 0)
            assert(Exercises.sumOfDivBy3Or5(3, 10) == 33)
            assert(Exercises.sumOfDivBy3Or5(0,15) == 60)
            assert(Exercises.sumOfDivBy3Or5(0,15) != 75)
            assert(Exercises.sumOfDivBy3Or5(0, 200) == 9368)
        }

        'test_primeFactors - {
            assert(Exercises.primeFactors(10) ==Seq(2,5))
            assert(Exercises.primeFactors(100) == Seq(2,5))
            assert(Exercises.primeFactors(2) == Seq(2))
            assert(Exercises.primeFactors(2) != Seq())
            assert(Exercises.primeFactors(-10) == Seq(2, 5, -1))
            assert(Exercises.primeFactors(1) == Seq())
            assert(Exercises.primeFactors(80) == Seq(2, 5))
            assert(Exercises.primeFactors(98) == Seq(2, 7))
        }

        'test_sumScalars - {
            val vecA0 = Vector2D(1.0, 2.0)
            val vecA1 = Vector2D(3.0, 4.0)
            val vecB0 = Vector2D(5.0, 6.0)
            val vecB1 = Vector2D(7.0, 8.0)
            assert(sumScalars(vecA0,vecA1,vecB0,vecB1)==94d)
        }

        'test_sumCosines - {
            val vecA0 = Vector2D(1.0, 2.0)
            val vecA1 = Vector2D(3.0, 4.0)
            val vecB0 = Vector2D(5.0, 6.0)
            val vecB1 = Vector2D(7.0, 8.0)
            assert(sumCosines(vecA0,vecA1,vecB0,vecB1) - 1.9835797 <= 0.00001)
        }

        'test_sortByHeavyweight - {
            val balls: Map[String, (Int, Double)] =
                Map(
                    "Aluminum" -> (3, 2.6889), "Tungsten" -> (2, 19.35), "Graphite" -> (12, 2.1), "Iron" -> (3, 7.874),
                    "Gold" -> (2, 19.32), "Potassium" -> (14, 0.862), "Calcium" -> (8, 1.55), "Cobalt" -> (4, 8.90),
                    "Lithium" -> (12, 0.534), "Magnesium" -> (10, 1.738), "Copper" -> (3, 8.96), "Sodium" -> (5, 0.971),
                    "Nickel" -> (2, 8.91), "Tin" -> (1, 7.29), "Platinum" -> (1, 21.45), "Plutonium" -> (3, 19.25),
                    "Lead" -> (2, 11.336), "Titanium" -> (2, 10.50), "Silver" -> (4, 4.505), "Uranium" -> (2, 19.04),
                    "Chrome" -> (3, 7.18), "Cesium" -> (7, 1.873), "Zirconium" -> (3, 6.45)
                )

            val expect = List(
                "Tin", "Platinum", "Nickel", "Aluminum", "Titanium", "Lead", "Sodium", "Uranium", "Gold",
                "Tungsten", "Zirconium", "Chrome", "Iron", "Copper", "Silver", "Plutonium", "Cobalt", "Cesium",
                "Calcium", "Lithium", "Magnesium", "Potassium", "Graphite"
            )

            assert(Exercises.sortByHeavyweight(balls) == expect)
        }
    }
}
