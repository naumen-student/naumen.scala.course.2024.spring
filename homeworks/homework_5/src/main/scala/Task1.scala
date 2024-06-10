@@ -1,3 +1,4 @@
object Task1 extends App {


  trait Show[-A] {
    def show(a: A): String
  }

  sealed trait Cat {
    def name: String
  }
  @@ -35,7 +36,6 @@
object Task1 extends App {
    case class NormalCat(name: String) extends Cat
    case class BigCat(name: String) extends Cat
    case class VeryBigCat(name: String) extends Cat

    sealed trait Box[+A] {
      def value: A
    }
    @@ -45,14 +45,24 @@

object Task1 extends App {
    }

    object ShowInstance {
      import Task1.ShowSyntax.ShowOps
      implicit val catShow: Show[Cat] = {
        case VeryLittleCat(name) => s"очень маленький кот $name"
        case LittleCat(name) => s"маленький кот $name"
        case NormalCat(name) => s"кот $name"
        case BigCat(name) => s"большой кот $name"
        case VeryBigCat(name) => s"очень большой кот $name"
      }


    implicit def boxShow[A: Show]: Show[Box[A]] = {
      case EmptyBox => "пустая коробка"
      case BoxWith(cat) => s"${cat.show} в коробке"
    }

        object ShowSyntax {
          implicit class ShowOps[A](val a: A) {
            def show(implicit show: Show[A]): String = show.show(a)
          }
        }
    }
  }
}