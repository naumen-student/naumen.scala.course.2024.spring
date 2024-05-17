package ru.naumen.scala_course.JobAggregator

import zio.{App, ExitCode, URIO, ZEnv}
import ru.naumen.scala_course.JobAggregator.JobAggregatorServer.appEnvironment

object Main extends App {

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    (JobAggregatorServer.server
      .provideSomeLayer[ZEnv](appEnvironment)
      .tapError(err => zio.console.putStrLn(s"Execution failed with: $err")))
      .exitCode
  }


}
