package example

object Main {
  def main(args: Array[String]): Unit = {
    val myName = "Grigoriy Babintsev"
    val words = Array("Hello", "Hola", "Guten tag")

    printHello(words, myName)
  }

  def printHello(words: Array[String], name: String): Unit = {
    val revName = name.reverse
    for(word <- words) println(s"$word Scala! This is $name")
    for(word <- words) println(s"$word Scala! This is $revName")
  }
}
