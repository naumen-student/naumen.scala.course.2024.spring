import Exercises.{Vector2D, cosBetween, divBy3Or7, primeFactor, scalar, sortByHeavyweight, sumCosines, sumOfDivBy3Or5, sumScalars}
import utest._

object Test extends TestSuite{

    val tests = Tests{
        Symbol("test_divBy3Or7") - {
            assert(divBy3Or7(1, 3) == Seq(3))
            assert(divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }
        Symbol("test_sumOfDivBy3Or5") - {
            assert(sumOfDivBy3Or5(1, 10) == 33)
            assert(sumOfDivBy3Or5(10, 20) == 75)
            assert(sumOfDivBy3Or5(1, 5) == 8)
            assert(sumOfDivBy3Or5(0, 0) == 0)
        }
        Symbol("test_primeFactor") - {
            assert(primeFactor(80) == Seq(2, 5))
            assert(primeFactor(98) == Seq(2, 7))
            assert(primeFactor(100) == Seq(2, 5))
            assert(primeFactor(13) == Seq(13))
            assert(primeFactor(1) == Seq())
        }
        Symbol("test_sumScalars") - {
            val vec1 = Vector2D(1, 2)
            val vec2 = Vector2D(3, 4)
            val vec3 = Vector2D(5, 6)
            val vec4 = Vector2D(7, 8)
            assert(sumScalars(vec1, vec2, vec3, vec4) == (scalar(vec1, vec2) + scalar(vec3, vec4)))

        }
        Symbol("test_sumCosines") - {
            val vec1 = Vector2D(1, 2)
            val vec2 = Vector2D(3, 4)
            val vec3 = Vector2D(5, 6)
            val vec4 = Vector2D(7, 8)

            assert(sumCosines(vec1, vec2, vec3, vec4) == (cosBetween(vec1, vec2) + cosBetween(vec3, vec4)))
        }
        Symbol("test_sortByHeavyweight") - {
            val sortedBalls = sortByHeavyweight()
            assert(sortedBalls.head == "Tin")
            assert(sortedBalls.last == "Graphite")
            assert(sortedBalls.contains("Gold"))
            assert(sortedBalls.contains("Silver"))
        }
    }
}
