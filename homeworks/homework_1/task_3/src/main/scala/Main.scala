object Main extends App {
  override def main(args: Array[String]): Unit = {
    val lastName = "Urusov"
    val name = "Sergey"
    val greetings = List[String]("Hello", "Hola", "Guten Tag")

    def helloScala(greating: String, name: String, lastName: String): Unit = {
      println(s"$greating, Scala! This is $name $lastName")
    }

    for (g <- greetings) {
      helloScala(g, name, lastName)
    }

    helloScala(greetings(0), name.reverse, lastName.reverse)
  }
}