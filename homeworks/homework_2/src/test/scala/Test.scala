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
            assert(Exercises.primeFactors(100) == Seq(2,2,5,5))
            assert(Exercises.primeFactors(2) == Seq(2))
            assert(Exercises.primeFactors(2) != Seq())
            assert(Exercises.primeFactors(-10) == Seq())
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
            val metals = Exercises.sortByHeavyweight(Exercises.balls)
            assert(metals.nonEmpty)
            assert(metals.length==Exercises.balls.size)
            assert(metals.last.equals("Graphite"))
            assert(metals.head.equals("Tin"))
        }        
    }
}
