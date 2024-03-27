package example

object Main {
  def main(args: Array[String]): Unit = {
    val name: String = "Artem Malevannyi"
    print_hello("Hello", name)
    print_hello("Hola", name)
    print_hello("Guten Tag", name)
    print_hello("Hello", name.reverse)
    print_hello("Hola", name.reverse)
    print_hello("Guten Tag", name.reverse)
  }

  def print_hello(hello_word: String, user_name: String): Unit = {
    println(hello_word + " Scala! This is " + user_name)
  }
}
