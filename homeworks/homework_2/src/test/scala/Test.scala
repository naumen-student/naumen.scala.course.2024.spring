import Exercises.{Vector2D, sumCosines}
import utest._

object Test extends TestSuite{

    val tests = Tests{
        'test_divBy3Or7 - {
            assert(Exercises.divBy3Or7(1, 3) == Seq(3))
            assert(Exercises.divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }
        'test_sumOfDivBy3Or5 - {
            assert(Exercises.sumOfDivBy3Or5(1, 3) == 3)
            assert(Exercises.sumOfDivBy3Or5(6, 6) == 6)
            assert(Exercises.sumOfDivBy3Or5(5, 9) == 20)
            assert(Exercises.sumOfDivBy3Or5(-7, 3) == -11)
            assert(Exercises.sumOfDivBy3Or5(0, 1) == 0)

            val thrown = intercept[IllegalArgumentException] {
                Exercises.sumOfDivBy3Or5(50, 0)
            }
            assert(thrown.getMessage == "iFrom must be less or equal then iTo")
        }
        'test_primeFactor - {
            assert(Exercises.primeFactor(80) == Seq(2, 5))
            assert(Exercises.primeFactor(98) == Seq(2, 7))
            assert(Exercises.primeFactor(3) == Seq(3))
            assert(Exercises.primeFactor(0) == Seq())
            assert(Exercises.primeFactor(-44) == Seq(2, 11))
            assert(Exercises.primeFactor(-4) == Seq(2))

        }

        'test_vector_abs - {
            assert(Exercises.abs(Vector2D(1,0)) == 1)
            assert(Exercises.abs(Vector2D(3,3)).round == 4)

        }
        'test_vector_scalar - {
            assert(Exercises.scalar(Vector2D(1, 4), Vector2D(6, 8)) == 38)
            assert(Exercises.scalar(Vector2D(-1, 0), Vector2D(-6, 4)) == 6)
            assert(Exercises.scalar(Vector2D(0, 0), Vector2D(0, 0)) == 0)
        }
        'test_vector_SuMscalars - {
            assert(Exercises.sumScalars(Vector2D(1, 2), Vector2D(3, 5), Vector2D(4, 5), Vector2D(7, 9)) == 86)
            assert(Exercises.sumScalars(Vector2D(0, -4), Vector2D(5, 5), Vector2D(0, 0), Vector2D(10, -10)) == -20)
        }
        'test_vector_SuMcosines - {
            assert(Exercises.sumCosines(Vector2D(2, 20), Vector2D(4, 5), Vector2D(1, 6), Vector2D(0, 8)) == 1.825546910388621)
            assert(Exercises.sumCosines(Vector2D(1, 0), Vector2D(0, 1), Vector2D(1, 0), Vector2D(0, 1)) == 0)
        }
        
        'test_vector_HeavyWeight - {
            assert(Exercises.sortByHeavyweight() == Vector("Tin", "Platinum", "Nickel", "Aluminum", "Titanium", "Lead", "Sodium", "Uranium", "Gold", "Tungsten", "Zirconium", "Chrome", "Iron", "Copper", "Silver", "Plutonium", "Cobalt", "Cesium", "Calcium", "Lithium", "Magnesium", "Potassium", "Graphite"))

        }

    }
}
