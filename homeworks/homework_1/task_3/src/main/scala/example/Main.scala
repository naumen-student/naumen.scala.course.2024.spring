package example

object Main extends App {
    val greetings = Array("Hello", "Hola", "Guten tag");
    val name = "Olga";
    greetings.foreach(greeting => println(s"$greeting Scala! This is $name "));
    greetings.foreach(greeting =>
      println(s"$greeting Scala! This is ${name.reverse}")
    );
}
