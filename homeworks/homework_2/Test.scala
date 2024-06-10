import Exercises.Vector2D
import utest._

object Test extends TestSuite{

  val tests = Tests{
    'test_divBy3Or7 - {
      assert(Exercises.divBy3Or7(1, 3) == Seq(3))
      assert(Exercises.divBy3Or7(2, 9) == Seq(6, 7, 9))
      assert(Exercises.divBy3Or7(0, 100) == Seq(0, 3, 6, 7, 9, 12, 14, 15, 18, 21, 24, 27, 28, 30, 33, 35, 36, 39, 42, 45, 48, 49, 51, 54, 56, 57, 60, 63, 66, 69, 70, 72, 75, 77, 78, 81, 84, 87, 90, 91, 93, 96, 98, 99))
    }

    'test_divBy3Or7 - {
      assert(Exercises.sumOfDivBy3Or5(1, 3) == 3)
      assert(Exercises.sumOfDivBy3Or5(5, 10) == 30)
      assert(Exercises.sumOfDivBy3Or5(-3, 6) == 11)
    }

    'test_primeFactor - {
      assert(Exercises.primeFactor(7) == Seq(7))
      assert(Exercises.primeFactor(17) == Seq(17))
      assert(Exercises.primeFactor(50) == Seq(2, 5, 5))
    }

    'test_sumScalars - {
      val leftVec0 = Vector2D(0, 0)
      val leftVec1 = Vector2D(2, 4)
      val rightVec0 = Vector2D(4, 4)
      val rightVec1 = Vector2D(1, 1)

      assert(Exercises.sumScalars(leftVec0, leftVec1, rightVec0, rightVec1) == 8)
    }

    'test_sumCosines - {
      val leftVec0 = Vector2D(3, 4)
      val leftVec1 = Vector2D(4, 3)
      val rightVec0 = Vector2D(7, 1)
      val rightVec1 = Vector2D(5, 5)
      val inaccuracy = 0.001

      val result = Exercises.sumCosines(leftVec0, leftVec1, rightVec0, rightVec1)

      assert(1.7599 - inaccuracy < result && result < 1.7599 + inaccuracy)
    }

    'test_sortByHeavyweight - {
      val balls: Map[String, (Int, Double)] = Map(
        "Aluminum" -> (3,   2.6889), "Tungsten" ->  (2,   19.35), "Graphite" ->  (12,  2.1),   "Iron" ->      (3,   7.874)
      )

      assert(Exercises.sortByHeavyweight(balls) == Seq("Aluminum", "Tungsten", "Graphite", "Iron"))
    }
  }
}