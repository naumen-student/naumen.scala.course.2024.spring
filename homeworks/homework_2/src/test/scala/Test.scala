import utest._

object Test extends TestSuite {
    val tests = Tests {
        'test_divBy3Or7 - {
            assert(Exercises.divBy3Or7(1, 3) == Seq(3))
            assert(Exercises.divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }
        'test_sumOfDivBy3Or5 - {
            assert(Exercises.sumOfDivBy3Or5(1, 10) == 33)
            assert(Exercises.sumOfDivBy3Or5(1, 20) == 98)
            assert(Exercises.sumOfDivBy3Or5(-10, 10) == 0)
        }
        'test_primeFactor - {
            assert(Exercises.primeFactor(10) == Seq(2,5))
            assert(Exercises.primeFactor(9) == Seq(3))
            assert(Exercises.primeFactor(11) == Seq(11))

        }
        'test_sortByHeavyweight - {
            val balls: Map[String, (Int, Double)] = Map(
                "Aluminum" -> (3,   2.6889), "Tungsten" ->  (2,   19.35), "Graphite" ->  (12,  2.1),   "Iron" ->      (3,   7.874)
            )

            assert(Exercises.sortByHeavyweight(balls) == Seq("Aluminum", "Tungsten", "Graphite", "Iron"))
        }
    }
}