import utest._

object Test extends TestSuite{

    val tests = Tests{
        'test_divBy3Or7 - {
            assert(Exercises.divBy3Or7(1, 3) == Seq(3))
            assert(Exercises.divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }

        'test_sumOfDivBy3Or5 - {
            assert(Exercises.sumOfDivBy3Or5(0, 5) == 8L)
            assert(Exercises.sumOfDivBy3Or5(0, 15) == 60L)
            assert(Exercises.sumOfDivBy3Or5(21,31) == 127L)
            assert(Exercises.sumOfDivBy3Or5(-10, 10) ==  0)
        }

        'test_primeFactor - {
            assert(Exercises.primeFactor(10) == Seq(2, 5))
            assert(Exercises.primeFactor(80) == Seq(2, 5))
            assert(Exercises.primeFactor(98)== Seq(2, 7))
            assert(Exercises.primeFactor(330) == Seq(2, 3, 5, 11))
            assert(Exercises.primeFactor(121) == Seq(11))
        }

        val vectors = Array(
            Exercises.Vector2D(1, 2),
            Exercises.Vector2D(3, 4),
            Exercises.Vector2D(5, 6),
            Exercises.Vector2D(7, 8),
            Exercises.Vector2D(2, -1),
            Exercises.Vector2D(-1, -2)
        )

        'test_sumScalars - {
            assert(Exercises.sumScalars(vectors(0), vectors(1), vectors(2), vectors(3)) == 94.0)
            assert(Exercises.sumScalars(vectors(0), vectors(1), vectors(4), vectors(5)) == 11.0)
        }

        'test_sumCosines - {
            assert(Exercises.sumCosines(vectors(0), vectors(1), vectors(2), vectors(3)) == 1.9835797185673898)
            assert(Exercises.sumCosines(vectors(0), vectors(1), vectors(4), vectors(5)) == 0.9838699100999074)
        }

        'test_sortByHeavyweight - {
            val have = Exercises.sortByHeavyweight(Exercises.balls)
            val want = Seq(
                "Tin", "Platinum", "Nickel", "Aluminum", "Titanium", "Lead",
                "Sodium", "Uranium", "Gold", "Tungsten", "Zirconium", "Chrome",
                "Iron", "Copper", "Silver", "Plutonium", "Cobalt", "Cesium",
                "Calcium", "Lithium", "Magnesium", "Potassium", "Graphite"
            )
            assert(have == want)
        }
    }
}
