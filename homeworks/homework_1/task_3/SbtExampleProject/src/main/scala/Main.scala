object Main {
  def main(args: Array[String]): Unit = {
    val name = "Gleb"
    def makeGreetingsForName(name: String, languageGreetings: Array[String]) : Array[String] = {languageGreetings.map(greeting => f"$greeting Scala! This is $name")}
    def printFullGreetings(greetings: Array[String]) : Unit = {greetings.foreach(greeting => println(greeting))}
    def makeAndPrintGreetings(name: String, languageGreetings: Array[String]) : Unit = {printFullGreetings(makeGreetingsForName(name,languageGreetings))}
    val foreignLanguageGreetings = Array("Hola","Bonjour","Guten Tag","Privet")
    makeAndPrintGreetings(name,Array("Hello"))
    makeAndPrintGreetings(name,foreignLanguageGreetings)
    makeAndPrintGreetings(name.reverse,foreignLanguageGreetings)
  }
}