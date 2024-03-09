object Main {
  @main def run() =
    def message(language: String, name: String): Unit = {
      println(language + ", Scala! " + name + " here");
    }

    var name = "Sashka";
    var languages = Array("Hello", "Hola", "Bonjour")

    languages.foreach(message(_, name))

    var unoReverse = name.reverse;

    languages.foreach(message(_, unoReverse))
}
