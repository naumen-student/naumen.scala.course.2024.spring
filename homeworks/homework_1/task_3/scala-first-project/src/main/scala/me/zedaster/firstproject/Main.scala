package me.zedaster.firstproject

object Main {
  def main(args: Array[String]): Unit = {
    val name = "Sergey"
    val text = s"Hello Scala! This is $name"
    println(text)

    val holaText : String = text.replaceFirst("Hello", "Hola")
    println(holaText)

    val germanText = text.replaceFirst("Hello", "Guten Tag")
    println(germanText)
  }
}