package ru.dru

import zio.CanFail.canFailAmbiguous1
import zio.{Duration, Exit, Fiber, Scope, ZIO, ZIOApp, ZIOAppArgs, ZIOAppDefault, durationInt}
import zio.{Duration, Scope, UIO, ZIO, ZIOAppArgs, ZIOAppDefault, durationInt}

import java.time.LocalDateTime
import scala.concurrent.TimeoutException

case class SaladInfoTime(tomatoTime: Duration, cucumberTime: Duration)


object Breakfast extends ZIOAppDefault {

  /**
   * Функция должна эмулировать приготовление завтрака. Продолжительные операции необходимо эмулировать через ZIO.sleep.
   * Правила приготовления следующие:
   *  1. Нобходимо вскипятить воду (время кипячения waterBoilingTime)
   *  2. Параллельно с этим нужно жарить яичницу eggsFiringTime
   *  3. Параллельно с этим готовим салат:
   *    * сначала режим  огурцы
   *    * после этого режим помидоры
   *    * после этого добавляем в салат сметану
   *  4. После того, как закипит вода необходимо заварить чай, время заваривания чая teaBrewingTime
   *  5. После того, как всё готово, можно завтракать
   *
   * @param eggsFiringTime время жарки яичницы
   * @param waterBoilingTime время кипячения воды
   * @param saladInfoTime информация о времени для приготовления салата
   * @param teaBrewingTime время заваривания чая
   * @return Мапу с информацией о том, когда завершился очередной этап (eggs, water, saladWithSourCream, tea)
   */
  def makeBreakfast(eggsFiringTime: Duration,
                    waterBoilingTime: Duration,
                    saladInfoTime: SaladInfoTime,
                    teaBrewingTime: Duration): ZIO[Any, Throwable, Map[String, LocalDateTime]] = ???



  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = ZIO.succeed(println("Done"))

}
                    teaBrewingTime: Duration): ZIO[Any, Throwable, Map[String, LocalDateTime]] = {

    def currentTime: UIO[LocalDateTime] = ZIO.succeed(LocalDateTime.now)

    val boilWater = ZIO.sleep(waterBoilingTime) *> currentTime
    val fryEggs = ZIO.sleep(eggsFiringTime) *> currentTime
    val prepareSalad = for {
      _ <- ZIO.sleep(saladInfoTime.cucumberTime)
      _ <- ZIO.sleep(saladInfoTime.tomatoTime)
      time <- currentTime
    } yield time

    for {
      // Start boiling water and frying eggs concurrently
      waterTimeFiber <- boilWater.fork
      eggsTimeFiber <- fryEggs.fork
      saladTimeFiber <- prepareSalad.fork

      // Wait for water to boil
      waterTime <- waterTimeFiber.join

      // Start brewing tea as soon as water is boiled
      teaTimeFiber <- (ZIO.sleep(teaBrewingTime) *> currentTime).fork

      // Wait for the rest of the tasks to complete
      eggsTime <- eggsTimeFiber.join
      saladTime <- saladTimeFiber.join
      teaTime <- teaTimeFiber.join

    } yield Map(
      "eggs" -> eggsTime,
      "water" -> waterTime,
      "saladWithSourCream" -> saladTime,
      "tea" -> teaTime
    )
  }

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    val eggsFiringTime = 5.seconds
    val waterBoilingTime = 10.seconds
    val saladInfoTime = SaladInfoTime(3.seconds, 4.seconds)
    val teaBrewingTime = 2.seconds

    makeBreakfast(eggsFiringTime, waterBoilingTime, saladInfoTime, teaBrewingTime).flatMap { result =>
      ZIO.succeed(println(result))
    }
  }
}
