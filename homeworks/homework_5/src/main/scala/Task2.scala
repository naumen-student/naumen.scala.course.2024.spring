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
  import cats.Monoid
  import cats.syntax.monoid._

  case class RadiusVector(x: Int, y: Int)
  object RadiusVector {
    implicit val monoid: Monoid[RadiusVector] = new Monoid[RadiusVector] {
      def empty: RadiusVector = RadiusVector(0, 0)
      def combine(a: RadiusVector, b: RadiusVector): RadiusVector =
        RadiusVector(a.x + b.x, a.y + b.y)
    }
  }

  case class DegreeAngle(angel: Double)
  object DegreeAngle {
    implicit val monoid: Monoid[DegreeAngle] = new Monoid[DegreeAngle] {
      def empty: DegreeAngle = DegreeAngle(0)
      def combine(a: DegreeAngle, b: DegreeAngle): DegreeAngle =
        DegreeAngle((a.angel + b.angel) % 360)
    }
  }

  case class SquareMatrix[A: Monoid](values: ((A, A, A), (A, A, A), (A, A, A)))
  object SquareMatrix {
    implicit def monoid[A: Monoid]: Monoid[SquareMatrix[A]] = new Monoid[SquareMatrix[A]] {
      def empty: SquareMatrix[A] = SquareMatrix(
        (Monoid[A].empty, Monoid[A].empty, Monoid[A].empty),
        (Monoid[A].empty, Monoid[A].empty, Monoid[A].empty),
        (Monoid[A].empty, Monoid[A].empty, Monoid[A].empty)
      )

      override def combine(a: SquareMatrix[A], b: SquareMatrix[A]): SquareMatrix[A] =
        SquareMatrix(a.values.combine(b.values))
    }
  }

  val radiusVectors = Vector(RadiusVector(0, 0), RadiusVector(0, 1), RadiusVector(-1, 1))
  println(Monoid[RadiusVector].combineAll(radiusVectors)) // RadiusVector(-1, 2)

  val gradeAngles = Vector(DegreeAngle(380), DegreeAngle(60), DegreeAngle(30))
  println(Monoid[DegreeAngle].combineAll(gradeAngles)) // DegreeAngle(90)

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
