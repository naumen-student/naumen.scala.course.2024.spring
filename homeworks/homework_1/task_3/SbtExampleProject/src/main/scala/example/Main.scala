package example

object Main extends App {
  private val greetings = List("Hello", "Hola", "Guten tag")
  private val names = List("Andrey Dikov", "yerdnA vokiD")
  for (name <- names) {
    for (greeting <- greetings) println(s"$greeting Scala! This is $name")
  }
}
