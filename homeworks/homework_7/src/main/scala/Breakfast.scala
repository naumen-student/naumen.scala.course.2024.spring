package ru.dru

import java.time.LocalDateTime


@@ -15,25 +15,39 @@
object Breakfast extends ZIOAppDefault {
/**
  @param eggsFiringTime время жарки яичницы
  @param waterBoilingTime время кипячения воды
  @param saladInfoTime информация о времени для приготовления салата
  @param teaBrewingTime время заваривания чая
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
  }
  yield Map("eggs" -> eggsTime,
    "water" -> waterTime,
    "saladWithSourCream" -> saladTime,
    "tea" -> teaTime)


  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = ZIO.succeed(println("Done"))