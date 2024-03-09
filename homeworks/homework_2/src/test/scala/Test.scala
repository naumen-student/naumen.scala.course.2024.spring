import Exercises.{Vector2D, cosBetween, scalar}
import utest._

object Test extends TestSuite{

    val tests = Tests{
        'test_divBy3Or7 - {
            assert(Exercises.divBy3Or7(1, 3) == Seq(3))
            assert(Exercises.divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }
    }
    val testsTask1 = Tests{
        'test_sumOfDivBy3Or5 - {
            assert(Exercises.sumOfDivBy3Or5(1, 3) == 3)
            assert(Exercises.sumOfDivBy3Or5(5, 9) == 20)
            assert(Exercises.sumOfDivBy3Or5(1, 2) == 0)
            assert(Exercises.sumOfDivBy3Or5(0, 20) == 113)
        }
    }
    val testsTask1_way2 = Tests{
        'test_sumOfDivBy3Or5_way2 - {
            assert(Exercises.sumOfDivBy3Or5_way2(1, 3) == 3)
            assert(Exercises.sumOfDivBy3Or5_way2(5, 9) == 20)
            assert(Exercises.sumOfDivBy3Or5_way2(1, 2) == 0)
            assert(Exercises.sumOfDivBy3Or5_way2(0, 20) == 113)
        }
    }
    val testsTask2 = Tests{
        'test_primeFactor - {
            assert(Exercises.primeFactor(7) == Seq(7))
            assert(Exercises.primeFactor(80) == Seq(2, 5))
            assert(Exercises.primeFactor(90) == Seq(2,7))
        }
    }
    val testsTask3 = Tests{
        'test_Task3 - {
            val left0 = Vector2D(1, 1);
            val left1 = Vector2D(3, -1);
            val right0 = Vector2D(0, 5);
            val right1 = Vector2D (2.5, 4);
            assert(Exercises.sumScalars(left0, left1, right0, right1) == scalar(left0, left1) + scalar(right0, right1))
            assert(Exercises.sumCosines(left0, left1, right0, right1) == cosBetween(left0, left1) + cosBetween(right0, right1))
        }
    }
}
