object Main extends App {
  def printHello(hello: String, name: String) = println(s"$hello Scala! This is $name")

  val greetings = Array("Hello", "Hola", "Guten tag")
  val name = "Daniil Garipov"

  greetings.foreach(greeting => printHello(greeting, name))

  greetings.foreach(greeting => printHello(greeting, name.reverse))
}