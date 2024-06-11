import Task2.SquareMatrix.monoid
import cats._
import cats.implicits._



/*
  Задание №2
  Всё просто, для каждого кейс класса необходимо описать логику его сложения.
  Радиус-вектор должен складываться, как и любой другой вектор.
  GradeAngle всегда выражает угол [0, 360).
  SquareMatrix просто сложение квадратных матриц
 */
object Task2 extends App {
  case class RadiusVector(x: Int, y: Int)
  object RadiusVector {
    implicit val monoid: Monoid[RadiusVector] = new Monoid[RadiusVector] {
      override def empty() = new RadiusVector(0,0)
      override def combine(vector1: RadiusVector, vector2: RadiusVector) =
        new RadiusVector(vector1.x + vector2.x, vector1.y + vector2.y)
    }
  }
  case class DegreeAngle(angle: Double)
  object DegreeAngle {
    implicit val monoid: Monoid[DegreeAngle] = new Monoid[DegreeAngle] {
      override def empty() = new DegreeAngle(0)
      override def combine(x: DegreeAngle, y: DegreeAngle) =
        new DegreeAngle((x.angle + y.angle) % 360)
    }
  }

  case class SquareMatrix[A : Monoid](values: ((A, A, A), (A, A, A), (A, A, A)))
  object SquareMatrix {
    implicit def monoid[A: Monoid]: Monoid[SquareMatrix[A]] = new Monoid[SquareMatrix[A]] {
      private val emptyVal = implicitly[Monoid[A]].empty

      override def empty() = new SquareMatrix[A](
        (emptyVal, emptyVal, emptyVal),
        (emptyVal, emptyVal, emptyVal),
        (emptyVal, emptyVal, emptyVal))

      override def combine(x: SquareMatrix[A], y: SquareMatrix[A]): SquareMatrix[A] = {
        val firstRow = getRow(x.values._1, y.values._1)
        val secondRow = getRow(x.values._2, y.values._2)
        val thirdRow = getRow(x.values._3, y.values._3)
        new SquareMatrix[A]((firstRow, secondRow, thirdRow))
      }
      private def getRow(tuple1: (A, A, A), tuple2: (A, A, A)) = {
        val func = (a, b) => implicitly[Monoid[A]].combine(a, b)
        (func(tuple1._1, tuple2._1), func(tuple1._2, tuple2._2), func(tuple1._3, tuple2._3))
      }
    }
  }

  val radiusVectors = Vector(RadiusVector(0, 0), RadiusVector(0, 1), RadiusVector(-1, 1))
  Monoid[RadiusVector].combineAll(radiusVectors) // RadiusVector(-1, 2)

  val gradeAngles = Vector(DegreeAngle(380), DegreeAngle(60), DegreeAngle(30))
  Monoid[DegreeAngle].combineAll(gradeAngles) // GradeAngle(90)

  val matrixes = Vector(
    SquareMatrix(
      (
        (1, 2, 3),
        (4, 5, 6),
        (7, 8, 9)
      )
    ),
    SquareMatrix(
      (
        (-1, -2, -3),
        (-3, -4, -5),
        (-7, -8, -9)
      )
    )
  )
  Monoid[SquareMatrix[Int]].combineAll(matrixes)
  //  [0, 0, 0]
  //  |1, 1, 1|
  //  [0, 0, 0]
}
