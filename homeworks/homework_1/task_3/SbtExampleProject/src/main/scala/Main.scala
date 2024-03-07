object Main {
  var greetings = List("Hello")
  def main(args: Array[String]): Unit = {
    val name = "George Lapp"
    greetings:+="Hola"
    greetings:+="Guten tag"
    printing(name)
  }
  def printing(name:String): Unit= {
    greetings.foreach(greet=>println(greet+" Scala! This is "+name))
    println()
    greetings.foreach(greet=>println(greet+" Scala! This is "+name.reverse))
  }
}