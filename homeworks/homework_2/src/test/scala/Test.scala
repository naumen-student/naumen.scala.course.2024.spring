import Exercises.{Vector2D, divBy3Or7, primeFactor, scalar, sumCosines, sumOfDivBy3Or5, sumOfDivBy3Or5V2, sumScalars, sortByHeavyweight}
import utest._

object Test extends TestSuite{

    val tests = Tests{
        Symbol("test_divBy3Or7") - {
            assert(divBy3Or7(1, 3) == Seq(3))
            assert(divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }
        Symbol("test_sumOfDivBy3Or5") - {
            assert(sumOfDivBy3Or5(1, 10) == 23)
            assert(sumOfDivBy3Or5(1, 5) == 8)
            assert(sumOfDivBy3Or5(10, 20) == 75)
            assert(sumOfDivBy3Or5(1, 2) == 0)
            assert(sumOfDivBy3Or5(4, 5) == 0)
        }
        Symbol("test_sumOfDivBy3Or5V2") - {
            assert(sumOfDivBy3Or5V2(1, 10) == 23)
            assert(sumOfDivBy3Or5V2(1, 5) == 8)
            assert(sumOfDivBy3Or5V2(10, 20) == 75)
            assert(sumOfDivBy3Or5V2(1, 2) == 0)
            assert(sumOfDivBy3Or5V2(4, 5) == 0)
        }
        Symbol("test_primeFactor") - {
            assert(primeFactor(80) == Seq(2, 5))
            assert(primeFactor(98) == Seq(2, 7))
            assert(primeFactor(120) == Seq(2, 3, 5))
            assert(primeFactor(7) == Seq(7))
            assert(primeFactor(17) == Seq(17))
            assert(primeFactor(1) == Seq())
            assert(primeFactor(-98) == Seq())
            assert(primeFactor(0) == Seq())
        }
        Symbol("test_sumScalars") - {
            val v1 = Vector2D(1, 3)
            val v2 = Vector2D(-2, 4)

            assert(sumScalars(v1, v2, v1, v2) == scalar(v1, v2) + scalar(v1, v2))

            val v3 = Vector2D(-1, 7)
            val v4 = Vector2D(0, -3)

            assert(sumScalars(v3, v4, v4, v3) == scalar(v3, v4) + scalar(v4, v3))
        }
        Symbol("test_sumCosines") - {
            val v1 = Vector2D(1, 3)
            val v2 = Vector2D(-2, 4)

            assert(sumCosines(v1, v2, v1, v2) == scalar(v1, v2) + scalar(v1, v2))

            val v3 = Vector2D(-1, 7)
            val v4 = Vector2D(0, -3)

            assert(sumCosines(v3, v4, v4, v3) == scalar(v3, v4) + scalar(v4, v3))
        }
        Symbol("test_sortByHeavyweightTest_1") - {
            val customBalls: Map[String, (Int, Double)] = Map("A" -> (1, 1.0), "B" -> (2, 2.0), "C" -> (3, 3.0))
            val expected = Seq("A", "B", "C")
            assert(sortByHeavyweight(customBalls) == expected)
        }
        Symbol("test_sortByHeavyweightTest_2") - {
            val emptyBalls: Map[String, (Int, Double)] = Map()
            val expected = Seq()
            assert(sortByHeavyweight(emptyBalls) == expected)
        }
        Symbol("test_sortByHeavyweightTest_3") - {
            val singleBall: Map[String, (Int, Double)] = Map("A" -> (1, 1.0))
            val expected = Seq("A")
            assert(sortByHeavyweight(singleBall) == expected)
        }
        Symbol("test_sortByHeavyweightTest_4") - {
            val largeBalls: Map[String, (Int, Double)] = Map("A" -> (1, 1.0), "B" -> (10, 10.0), "C" -> (2, 2.0), "D" -> (5, 5.0), "E" -> (3, 3.0))
            val expected = Seq("A", "C", "E", "D", "B")
            assert(sortByHeavyweight(largeBalls) == expected)
        }
    }
}
