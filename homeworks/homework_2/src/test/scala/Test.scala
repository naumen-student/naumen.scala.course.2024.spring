import Exercises.{Vector2D, sumCosines, sumScalars}
import utest._

object Test extends TestSuite{

    val tests: Tests = Tests {

        'test_sumOfDivBy3Or5 - {
            assert(Exercises.sumOfDivBy3Or5(0, 1) == 0)
            assert(Exercises.sumOfDivBy3Or5(3, 10) == 33)
            assert(Exercises.sumOfDivBy3Or5(0,15) == 60)
            assert(Exercises.sumOfDivBy3Or5(0,15) != 75)
            assert(Exercises.sumOfDivBy3Or5(0, 200) == 9368)
        }

        'test_primeFactor - {
            assert(Exercises.primeFactor(10) ==Seq(2,5))
            assert(Exercises.primeFactor(100) == Seq(2,2,5,5))
            assert(Exercises.primeFactor(2) == Seq(2))
            assert(Exercises.primeFactor(2) != Seq())
            assert(Exercises.primeFactor(-10) == Seq())
            assert(Exercises.primeFactor(1) == Seq())
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

        'test_sortByHeavyWeight - {
            val metals = Exercises.sortByHeavyweight(Exercises.balls)
            assert(metals.nonEmpty)
            assert(metals.length==Exercises.balls.size)
            assert(metals.last.equals("Graphite"))
            assert(metals.head.equals("Tin"))
        }
    }
}
