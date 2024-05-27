package Main

import scala.util.Random

object Main {
  private def print_greetings(name: String): Unit = {
    val greetings = Array("Hello Scala! This is ", "Hola Scala! Este es ", "Guten tag, Scala! Das ist ")
    val names = Array(name, name.reverse)
    println(greetings(Random.nextInt(greetings.length))+names(Random.nextInt(names.length)))
  }

  for(i <- 1 to 10){
    print_greetings("Vladimir Perepelkin")
  }

}