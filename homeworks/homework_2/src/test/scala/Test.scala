import utest._

object Test extends TestSuite{

    val tests = Tests{
        'test_divBy3Or7 - {
            assert(Exercises.divBy3Or7(1, 3) == Seq(3))
            assert(Exercises.divBy3Or7(5, 9) == Seq(6, 7, 9))
            assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
        }
        'test_sumOfDivBy3Or5 - {
            assert(Exercises.sumOfDivBy3Or5(0, 2) == 0)
            assert(Exercises.sumOfDivBy3Or5(5, 9) == 20)
            assert(Exercises.sumOfDivBy3Or5(1, 15) == 60)
        }
        'test_primeFactor - {
            assert(Exercises.primeFactor(0) == Seq())
            assert(Exercises.primeFactor(1) == Seq())
            assert(Exercises.primeFactor(2) == Seq(2))
            assert(Exercises.primeFactor(90) == Seq(2, 3, 5))
        }
        'test_sumByFunc - {
            val vector1 = Exercises.Vector2D(1, 10)
            val vector2 = Exercises.Vector2D(5, 13)
            val vector3 = Exercises.Vector2D(-6, 4)
            val vector4 = Exercises.Vector2D(5, 10)
            assert(Exercises.sumScalars(vector1, vector2, vector3, vector4) == Exercises.scalar(vector1, vector2) + Exercises.scalar(vector3, vector4))
            assert(Exercises.sumCosines(vector1, vector2, vector3, vector4) == Exercises.cosBetween(vector1, vector2) + Exercises.cosBetween(vector3, vector4))
        }
        'test_sortByHeavyweight - {
            val partOfBalls: Map[String, (Int, Double)] =
                Map("Aluminum" -> (8,   2.6889), "Tungsten" ->  (1,   19.35), "Graphite" ->  (4,  2.1),   "Iron" ->      (4,   7.874))
            assert(Exercises.sortByHeavyweight() == Seq("Tin", "Platinum", "Nickel", "Aluminum", "Titanium", "Lead", "Sodium", "Uranium", "Gold", "Tungsten", "Zirconium", "Chrome", "Iron", "Copper", "Silver", "Plutonium", "Cobalt", "Cesium", "Calcium", "Lithium", "Magnesium", "Potassium", "Graphite"))
            assert(Exercises.sortByHeavyweight(partOfBalls) == Seq("Tungsten", "Graphite", "Iron", "Aluminum"))
        }
    }
}
