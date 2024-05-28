package example

object Main {
  def main(args: Array[String]): Unit = {
    def say_hello(greetings_list: List[String], word_name: String): Unit = {
      for (word <- greetings_list) {
        println(s"$word Scala! This is ${word_name.reverse}")
      }
    }

    val greetings = List("Hello", "Hola", "Gutten tag")
    say_hello(greetings, "Dmitry Gryaznov")
  }
}
