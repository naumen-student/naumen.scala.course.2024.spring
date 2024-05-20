import zio.{Exit, Fiber, Scope, ZIO, ZIOAppArgs, ZIOAppDefault, durationInt}

object Concurrency extends ZIOAppDefault {

  val showerTime = ZIO.succeed("Taking a shower")
  val boilingWater = ZIO.succeed("Boiling water")
  val makingCoffee = ZIO.succeed("Making coffee")

  def printThreadName = s"${Thread.currentThread().getName}"

  //Fiber[E, A]

  def morning = for {
    _ <- showerTime.debug(printThreadName)
    _ <- boilingWater.debug(printThreadName)
    _ <- makingCoffee.debug(printThreadName)
  } yield ()

  def morningWithConcurrentShower = for {
    _ <- showerTime.debug(printThreadName).fork
    _ <- boilingWater.debug(printThreadName)
    _ <- makingCoffee.debug(printThreadName)
  } yield ()

  def concurrentMorning = for {
    showerFiber <- showerTime.debug(printThreadName).fork
    boilingWaterFiber <- boilingWater.debug(printThreadName).fork
    zippedFiber = showerFiber.zip(boilingWaterFiber)
    result <- zippedFiber.join.debug(printThreadName)
    _ <- ZIO.succeed(s"$result done.").debug(printThreadName) *> makingCoffee.debug(printThreadName)
  } yield ()

  val callFromMasha = ZIO.succeed("Call from Masha")

  val boilingWaterWithTime = boilingWater.debug(printThreadName) *> ZIO.sleep(5.seconds) *> ZIO.succeed("Boiling water ready!")

  def concurrentMorningWithCall = for {
    _ <- showerTime.debug(printThreadName)
    boilingWaterFiber <- boilingWaterWithTime.fork
    _ <- callFromMasha.debug(printThreadName).fork *> ZIO.sleep(2.seconds) *> boilingWaterFiber.interrupt.debug(printThreadName)
    _ <- ZIO.succeed("Ok let's go").debug(printThreadName)
  } yield ()

  val makingCoffeeWithTime = makingCoffee.debug(printThreadName) *> ZIO.sleep(5.seconds) *> ZIO.succeed("Coffee ready!")

  def concurrentMorningWithCoffeeAtHome = for {
    _ <- showerTime.debug(printThreadName)
    _ <- boilingWater.debug(printThreadName)
    coffeeFiber <- makingCoffeeWithTime.debug(printThreadName).fork.uninterruptible
    result <- callFromMasha.debug(printThreadName).fork *> coffeeFiber.interrupt.debug(printThreadName)
    _ <- result match {
      case Exit.Success(value) => ZIO.succeed("Sorry, staying at home. Come over.").debug(printThreadName)
      case _ => ZIO.succeed("Ok let's go. Interrupted.").debug(printThreadName)
    }
  } yield ()


  override def run = {
    concurrentMorningWithCoffeeAtHome
  }

}
