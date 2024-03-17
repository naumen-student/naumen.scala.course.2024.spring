@main def run() =
  def hello(greeting: String, name: String): Unit = {
    println(s"$greeting Scala! This is $name!")
  }
  var n: String = "Alexey Vlasov"
  hello("Hello", n)
  hello("Hola", n)
  hello("Guten tag", n)
  n = n.reverse
  hello("Hello", n)
  hello("Hola", n)
  hello("Guten tag", n)