@main



def main(): Unit= {
  def printMessage(name: String = "timur", surname: String = "zhakeev", isReversed: Boolean = true): String = {
    val nameR = if (isReversed) name.reverse else name
    s"aloha scala! This is $nameR $surname"
  }
  val name = "Timur"
  val surname = "Zhakeev"

  println(printMessage(name, surname, true))
}