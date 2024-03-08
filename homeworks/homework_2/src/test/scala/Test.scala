import Exercises.Vector2D
import utest._

object Test extends TestSuite{

    val tests = Tests{
        'test_divBy3Or7 - {
            assert(Exercises.divBy3Or7(1, 3) == Seq(3))
            assert(Exercises.divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }

        'test_sumOfDivBy3Or5 - {
            assert(Exercises.sumOfDivBy3Or5(3, 3) == 3)
            assert(Exercises.sumOfDivBy3Or5(5, 5) == 5)
            assert(Exercises.sumOfDivBy3Or5(12, 21) == 86)
            assert(Exercises.sumOfDivBy3Or5(21, 24) == 45)
            assert(Exercises.sumOfDivBy3Or5(0, 20) == 98)
            assert(Exercises.sumOfDivBy3Or5(-9, 6) == -9)
            val thrown = intercept[IllegalArgumentException] {
                Exercises.sumOfDivBy3Or5(12, 3) // предполагается, что этот вызов выбросит исключение IllegalArgumentException
            }
            assert(thrown.getMessage == "iFrom must be less or equal then iTo")
        }

        'test_primeFactor - {
            assert(Exercises.primeFactor(35) == Seq(5, 7))
            assert(Exercises.primeFactor(18) == Seq(2, 3))
            assert(Exercises.primeFactor(512) == Seq(2))
            assert(Exercises.primeFactor(36) == Seq(2, 3))
            assert(Exercises.primeFactor(69) == Seq(3, 23))
            assert(Exercises.primeFactor(100) == Seq(2, 5))
            assert(Exercises.primeFactor(6) == Seq(2, 3))
            assert(Exercises.primeFactor(23) == Seq(23))

            val thrownEqualOne = intercept[IllegalArgumentException] {
                Exercises.primeFactor(1) // предполагается, что этот вызов выбросит исключение IllegalArgumentException
            }
            val thrownEqualZero = intercept[IllegalArgumentException] {
                Exercises.primeFactor(0) // предполагается, что этот вызов выбросит исключение IllegalArgumentException
            }
            val thrownNegativeNumber = intercept[IllegalArgumentException] {
                Exercises.primeFactor(-23) // предполагается, что этот вызов выбросит исключение IllegalArgumentException
            }
            assert(thrownEqualOne.getMessage == "Number must be greater than 1")
            assert(thrownEqualZero.getMessage == "Number must be greater than 1")
            assert(thrownNegativeNumber.getMessage == "Number must be greater than 1")
        }

        "test_sumScalars" - {
            val leftVector0: Vector2D = Vector2D(8, 5)
            val leftVector1: Vector2D = Vector2D(3, 12)
            val rightVector0: Vector2D = Vector2D(6, 2)
            val rightVector1: Vector2D = Vector2D(7, 7)

            val result: Double = 140
            val epsScalars: Double = 1.0E-6

            assert(Math.abs(Exercises.sumScalars(leftVector0, leftVector1, rightVector0, rightVector1) - result) < epsScalars)
        }

        "test_sumCosines" - {
            val leftVector0: Vector2D = Vector2D(8, 5)
            val leftVector1: Vector2D = Vector2D(3, 12)
            val rightVector0: Vector2D = Vector2D(6, 2)
            val rightVector1: Vector2D = Vector2D(7, 7)

            val result: Double = 1.6142714
            val epsCosines: Double = 1.0E-6

            assert(Math.abs(Exercises.sumCosines(leftVector0, leftVector1, rightVector0, rightVector1) - result) < epsCosines)
        }

        'test_sortByHeavyWeight -{
            val expectedValidBalls: Seq[String] = Seq("Tin", "Platinum", "Nickel", "Aluminum", "Titanium", "Lead", "Sodium", "Uranium", "Gold", "Tungsten",
                "Zirconium", "Chrome", "Iron", "Copper", "Silver", "Plutonium", "Cobalt", "Cesium", "Calcium",
                "Lithium", "Magnesium", "Potassium", "Graphite")

            val invalidBallsRadius: Map[String, (Int, Double)] =
                Map(
                    "Aluminum" -> (-3, 2.6889), "Tungsten" -> (2, 19.35), "Graphite" -> (12, 2.1), "Iron" -> (3, 7.874),
                    "Gold" -> (2, 19.32), "Potassium" -> (14, 0.862), "Calcium" -> (8, 1.55), "Cobalt" -> (4, 8.90),
                    "Lithium" -> (12, 0.534), "Magnesium" -> (10, 1.738), "Copper" -> (3, 8.96), "Sodium" -> (5, 0.971),
                    "Nickel" -> (2, 8.91), "Tin" -> (1, 7.29), "Platinum" -> (1, 21.45), "Plutonium" -> (3, 19.25),
                    "Lead" -> (2, 11.336), "Titanium" -> (-2, 10.50), "Silver" -> (4, 4.505), "Uranium" -> (2, 19.04),
                    "Chrome" -> (3, 7.18), "Cesium" -> (7, 1.873), "Zirconium" -> (3, 6.45)
                )
            val invalidBallsDensity: Map[String, (Int, Double)] =
                Map(
                    "Aluminum" -> (3, -2.6889), "Tungsten" -> (2, 19.35), "Graphite" -> (12, 2.1), "Iron" -> (3, 7.874),
                    "Gold" -> (2, 19.32), "Potassium" -> (14, 0.862), "Calcium" -> (8, 1.55), "Cobalt" -> (4, 8.90),
                    "Lithium" -> (12, 0.534), "Magnesium" -> (10, 1.738), "Copper" -> (3, 8.96), "Sodium" -> (5, 0.971),
                    "Nickel" -> (2, 8.91), "Tin" -> (1, 7.29), "Platinum" -> (1, 21.45), "Plutonium" -> (3, 19.25),
                    "Lead" -> (2, 11.336), "Titanium" -> (2, 10.50), "Silver" -> (4, 4.505), "Uranium" -> (2, 19.04),
                    "Chrome" -> (3, 7.18), "Cesium" -> (7, -1.873), "Zirconium" -> (3, 6.45)
                )

            val thrownInvalidRadius = intercept[IllegalArgumentException] {
                Exercises.sortByHeavyweight(invalidBallsRadius) // предполагается, что этот вызов выбросит исключение IllegalArgumentException
            }

            val thrownInvalidDensity = intercept[IllegalArgumentException] {
                Exercises.sortByHeavyweight(invalidBallsDensity) // предполагается, что этот вызов выбросит исключение IllegalArgumentException
            }

            assert(Exercises.sortByHeavyweight() == expectedValidBalls)
            assert(thrownInvalidRadius.getMessage == "The radius of the balls cannot be less or equal than to zero")
            assert(thrownInvalidDensity.getMessage == "The density of the balls cannot be less or equal than to zero")
        }
    }
}
