package example

object Main extends App {
  val name = "Andrey"
  val greetings = List("Hello", "Hola", "Guten tag")
  val reversedName = "Andrey Zhunev".reverse

  greetings.foreach(greeting => println(s"$greeting Scala! This is $name"))

  println

  greetings.foreach(greeting => println(s"$greeting Scala! This is $reversedName"))
}
