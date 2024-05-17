package ru.naumen.scala_course.JobAggregator

import cats.effect.ConcurrentEffect
import org.http4s.client.Client
import org.http4s.client.blaze.BlazeClientBuilder
import zio.blocking.{Blocking, blocking}
import zio.{Has, Managed, Task, URIO, ZIO, ZLayer}
import zio.interop.catz._
import cats.effect._

import scala.concurrent.ExecutionContext

package object http {

  type HttpClient = Has[Client[Task]]

  object HttpClient{
    def mkHttpClient(clientEC: ExecutionContext): Managed[Throwable, Client[Task]] = ZIO.runtime[Any]
      .toManaged_.flatMap{ implicit rts =>
        BlazeClientBuilder[Task](clientEC).resource.toManagedZIO
      }

    val live: ZLayer[Blocking, Throwable, Has[Client[Task]]] = ZLayer.fromManaged(
      (for {
        blockingEC <- blocking {
          ZIO.descriptor.map(_.executor.asEC)
        }.toManaged_
        client <- mkHttpClient(blockingEC)
      } yield client)
    )
  }

  val client: URIO[Has[Client[Task]], Client[Task]] = ZIO.service[Client[Task]]
}
