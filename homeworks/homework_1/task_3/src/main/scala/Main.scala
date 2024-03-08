object Main {
  val name = "Matvey"
  val hello = "Hello"
  val hola = "Hola"
  val gut = "Guten tag"

  def main(args: Array[String]): Unit = {
    println(add(hello, name))
    println(add(hola, name))
    println(add(gut, name))
    println(add(hello, name.reverse))
    println(add(hola, name.reverse))
    println(add(gut, name.reverse))
  }


  def add(welc: String, name: String): String = {
    welc + " Scala! This is " + name
  }

}