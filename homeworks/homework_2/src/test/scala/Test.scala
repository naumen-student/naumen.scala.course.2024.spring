import Exercises.{Vector2D, balls}
import utest._

object Test extends TestSuite{

    val tests: Tests = Tests{
        'test_divBy3Or7 - {
            assert(Exercises.divBy3Or7(1, 3) == Seq(3))
            assert(Exercises.divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }

        'test_sumOfDivBy3Or5 - {
          assert(Exercises.sumOfDivBy3Or5(1, 10) == 33)
          assert(Exercises.sumOfDivBy3Or5(4, 10) == 30)
          assert(Exercises.sumOfDivBy3Or5(9, 9) == 9)
        }

        'test_primeFactor - {
          assert(Exercises.primeFactor(2*4*2*5).sorted == Seq(2, 5))
          assert(Exercises.primeFactor(9*9).sorted == Seq(3))

          assert(Exercises.primeFactor(2*2*7).sorted == Seq(2, 7))
          assert(Exercises.primeFactor(5) == Seq(5))
          assert(Exercises.primeFactor(0) == Seq())
          assert(Exercises.primeFactor(-11*2*2).sorted == Seq(2, 11))
          assert(Exercises.primeFactor(-4) == Seq(2))
        }

      'test_sortByHeavyweight - {
        val expect = List(
          "Tin", "Platinum", "Nickel", "Aluminum", "Titanium", "Lead", "Sodium", "Uranium", "Gold",
          "Tungsten", "Zirconium", "Chrome", "Iron", "Copper", "Silver", "Plutonium", "Cobalt", "Cesium",
          "Calcium", "Lithium", "Magnesium", "Potassium", "Graphite"
        )
        assert(Exercises.sortByHeavyweight(balls) == expect)
      }

      'test_scalar - {
        assert(Exercises.scalar(Vector2D(2, 2), Vector2D(3, 3)) == 12)

        assert(Exercises.scalar(Vector2D(2, 11), Vector2D(1, 1)) == 13)
        assert(Exercises.scalar(Vector2D(1, 1), Vector2D(2, 11)) == 13)

        assert(Exercises.scalar(Vector2D(2, 0), Vector2D(1, 0)) == 2)
        assert(Exercises.scalar(Vector2D(1, 0), Vector2D(2, 0)) == 2)

        assert(Exercises.scalar(Vector2D(2, 2), Vector2D(0, 0)) == 0)

      }

      'test_sumScalars - {
        assert(Exercises.sumScalars(Vector2D(2, 1), Vector2D(1, 3), Vector2D(5, 4), Vector2D(2, 1)) == 19)
        assert(Exercises.sumScalars(Vector2D(1, 1), Vector2D(1, 3), Vector2D(5, 4), Vector2D(2, 1)) == 18)
      }

      'test_cosBetween - {
        assert(Exercises.cosBetween(Vector2D(4, 0), Vector2D(0, 4)) == 0)
        assert(Exercises.cosBetween(Vector2D(1, 1), Vector2D(1, 0)) - 1/math.sqrt(2) <= 0.0000001)
        assert(Exercises.cosBetween(Vector2D(2, 2), Vector2D(3, 3)) - 12/math.sqrt(8)/math.sqrt(12) <= 0.0000001)
      }

      'test_sumCosines - {
        assert(Exercises.sumCosines(
          Vector2D(4, 0), Vector2D(0, 4), Vector2D(1, 1), Vector2D(1, 0))
          - 1/math.sqrt(2) <= 0.0000001)

        assert(Exercises.sumCosines(
          Vector2D(2, 2), Vector2D(3, 3), Vector2D(1, 1), Vector2D(1, 0))
          - 1 / math.sqrt(2) - 1/math.sqrt(2) - 12/math.sqrt(8)/math.sqrt(12) <= 0.0000001)
      }
    }
}
