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
      def empty: RadiusVector = RadiusVector(0, 0)
      def combine(a: RadiusVector, b: RadiusVector): RadiusVector =
        RadiusVector(a.x + b.x, a.y + b.y)
    }
  }

  case class DegreeAngle(angle: Double)
  object DegreeAngle {
    implicit val monoid: Monoid[DegreeAngle] = new Monoid[DegreeAngle] {
      def empty: DegreeAngle = DegreeAngle(0)
      def combine(a: DegreeAngle, b: DegreeAngle): DegreeAngle =
        DegreeAngle((a.angle + b.angle) % 360)
    }
  }

  case class SquareMatrix[A : Monoid](values: ((A, A, A), (A, A, A), (A, A, A)))
  object SquareMatrix {
    implicit def monoid[A: Monoid]: Monoid[SquareMatrix[A]] = new Monoid[SquareMatrix[A]] {
      def empty: SquareMatrix[A] = SquareMatrix(
        (Monoid[A].empty, Monoid[A].empty, Monoid[A].empty),
        (Monoid[A].empty, Monoid[A].empty, Monoid[A].empty),
        (Monoid[A].empty, Monoid[A].empty, Monoid[A].empty)
      )

      def combine(a: SquareMatrix[A], b: SquareMatrix[A]): SquareMatrix[A] = {
        val (a1, a2, a3) = a.values
        val (b1, b2, b3) = b.values
        SquareMatrix(
          (
            (Monoid[A].combine(a1._1, b1._1), Monoid[A].combine(a1._2, b1._2), Monoid[A].combine(a1._3, b1._3)),
            (Monoid[A].combine(a2._1, b2._1), Monoid[A].combine(a2._2, b2._2), Monoid[A].combine(a2._3, b2._3)),
            (Monoid[A].combine(a3._1, b3._1), Monoid[A].combine(a3._2, b3._2), Monoid[A].combine(a3._3, b3._3))
          )
        )
      }
    }
  }

  val radiusVectors = Vector(RadiusVector(0, 0), RadiusVector(0, 1), RadiusVector(-1, 1))
  println(Monoid[RadiusVector].combineAll(radiusVectors)) // RadiusVector(-1, 2)

  val degreeAngles = Vector(DegreeAngle(380), DegreeAngle(60), DegreeAngle(30))
  println(Monoid[DegreeAngle].combineAll(degreeAngles)) // DegreeAngle(90)

  val matrices = Vector(
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
  println(Monoid[SquareMatrix[Int]].combineAll(matrices))
}
