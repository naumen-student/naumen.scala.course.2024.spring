import utest._

object Test extends TestSuite{
    val tests = Tests{
        'test_divBy3Or7 - {
            assert(Exercises.divBy3Or7(1, 3) == Seq(3))
            assert(Exercises.divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }
        'test_sumOfDivBy3Or5 - {
            assert(Exercises.sumOfDivBy3Or5(1, 10) == 33)
            assert(Exercises.sumOfDivBy3Or5(10, 20) == 75)
            assert(Exercises.sumOfDivBy3Or5(0, 1) == 0)
            assert(Exercises.sumOfDivBy3Or5(25, 30) == 82)
            assert(Exercises.sumOfDivBy3Or5(95, 100) == 390)
        }
        'test_primeFactor - {
            assert(Exercises.primeFactor(80) == Seq(2, 5))
            assert(Exercises.primeFactor(98) == Seq(2, 7))
            assert(Exercises.primeFactor(100) == Seq(2, 5))
            assert(Exercises.primeFactor(144) == Seq(2, 3))
            assert(Exercises.primeFactor(13) == Seq(13))
            assert(Exercises.primeFactor(23432456) == Seq(2, 823, 3559))
        }
        'test_sumScalars - {
            assert(Exercises.sumScalars(
                Exercises.Vector2D(1.0, 2.0),
                Exercises.Vector2D(3.0, 4.0),
                Exercises.Vector2D(5.0, 6.0),
                Exercises.Vector2D(7.0, 8.0)
            ) == 94d)

            assert(Exercises.sumScalars(
                Exercises.Vector2D(3.0, 5.0),
                Exercises.Vector2D(5.0, 1.0),
                Exercises.Vector2D(4.0, 67.0),
                Exercises.Vector2D(4.0, 12.0)
            ) == 840d)

            assert(Exercises.sumScalars(
                Exercises.Vector2D(435.0, 123.0),
                Exercises.Vector2D(23.0, 54.0),
                Exercises.Vector2D(546.0, 123.0),
                Exercises.Vector2D(234.0, 765.0)
            ) == 238506d)
        }
        'test_sumCosines - {
            assert(Exercises.sumCosines(
                Exercises.Vector2D(1.0, 2.0),
                Exercises.Vector2D(3.0, 4.0),
                Exercises.Vector2D(5.0, 6.0),
                Exercises.Vector2D(7.0, 8.0)
            ) - 2.5250538 <= 0.00001)

            assert(Exercises.sumCosines(
                Exercises.Vector2D(3.0, 5.0),
                Exercises.Vector2D(5.0, 1.0),
                Exercises.Vector2D(4.0, 67.0),
                Exercises.Vector2D(4.0, 12.0)
            ) - 11.0568389 <= 0.00001)

            assert(Exercises.sumCosines(
                Exercises.Vector2D(435.0, 123.0),
                Exercises.Vector2D(23.0, 54.0),
                Exercises.Vector2D(546.0, 123.0),
                Exercises.Vector2D(234.0, 765.0)
            ) - 1.3783907 <= 0.00001)
        }
        'test_sortByHeavyweight - {
            val balls = Exercises.sortByHeavyweight(Exercises.balls)
            assert(balls.length == Exercises.balls.size)
            assert(balls.head.equals("Tin"))
            assert(balls.last.equals("Graphite"))
        }
    }
}
