package ru.dru
import zio.CanFail.canFailAmbiguous1
import zio.{Duration, Exit, Fiber, Scope, ZIO, ZIOApp, ZIOAppArgs, ZIOAppDefault, durationInt}
import java.time.LocalDateTime
import scala.concurrent.TimeoutException

case class SaladInfoTime(tomatoTime: Duration, cucumberTime: Duration)
object Breakfast extends ZIOAppDefault {
  
  /**
   * Функция должна эмулировать приготовление завтрака. Продолжительные операции необходимо эмулировать через ZIO.sleep.
   * Правила приготовления следующие:
	@@ -29,13 +25,25 @@ object Breakfast extends ZIOAppDefault {
   * @param teaBrewingTime время заваривания чая
   * @return Мапу с информацией о том, когда завершился очередной этап (eggs, water, saladWithSourCream, tea)
   */

  def makeBreakfast(eggsFiringTime: Duration,
                    waterBoilingTime: Duration,
                    saladInfoTime: SaladInfoTime,
                    teaBrewingTime: Duration): ZIO[Any, Throwable, Map[String, LocalDateTime]] = for {
    eggs <- ZIO.sleep(eggsFiringTime).as(LocalDateTime.now()).fork
    water <- ZIO.sleep(waterBoilingTime).as(LocalDateTime.now()).fork
    salad <- ZIO.sleep(saladInfoTime.tomatoTime.plus(saladInfoTime.cucumberTime)).as(LocalDateTime.now()).fork
    tea <- ZIO.sleep(teaBrewingTime).as(LocalDateTime.now()).fork
    eggsTime <- eggs.join
    waterTime <- water.join
    saladTime <- salad.join
    teaTime <- tea.join
  } yield Map(
    "eggs" -> eggsTime,
    "water" -> waterTime,
    "saladWithSourCream" -> saladTime,
    "tea" -> teaTime
  )

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = ZIO.succeed(println("Done"))
}