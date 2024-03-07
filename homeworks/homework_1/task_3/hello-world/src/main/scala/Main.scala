object Main extends App {
  def hello(hello_variation: String, name: String) = {
    println(hello_variation + " Scala! This is " + name)
  }

  def reverse_string(str: String): String = {
    return new StringBuffer(str).reverse.toString()
  }

  val name = "Ildar Tyakin"
  val hello1 = "Hello"
  val hello2 = "Hola"

  // 1
  hello(hello1, name)
  // 2
  hello(hello2, name)
  // 3
  hello(hello1, reverse_string(name))
  hello(hello2, reverse_string(name))
}