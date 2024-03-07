object Main {
  def main(args: Array[String]): Unit = {
    var fullName = "Ovechkin Ilya"
    val greetingWords = List("Hello", "Hola" , "Guten tag")

    for (word <- greetingWords) {
      GetGreetingPhrase(word, fullName)
      GetGreetingPhrase(word, fullName.reverse)
    }
  }

  def GetGreetingPhrase(helloWord : String, name : String) : Unit = println(s"$helloWord Scala! This is $name")
}