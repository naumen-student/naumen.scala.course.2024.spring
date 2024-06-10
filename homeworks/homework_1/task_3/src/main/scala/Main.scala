object Main {
  def main(args: Array[String]): Unit = {
    val helloWorld = "Hello Scala! This is Olga Trofimova"

    val hola = "Hola Scala! This is Olga Trofimova"

    val gutenTag = "Guten tag".concat(hola.substring(4))

    var words = hola.split(' ')
    words(4) = words(4).reverse
    words(5) = words(5).reverse
    val reversedHello = words.mkString(" ")

    println(Array(helloWorld, hola, gutenTag, reversedHello).mkString("\n"))

  }
}