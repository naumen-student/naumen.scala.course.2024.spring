package ru.naumen.scala_course.JobAggregator.Entities.services

import zio.{Has, Task, UIO, URIO, ZIO, ZLayer}

object EntitiesService {

    type EntitiesService = Has[Service]

    trait Service{

    }

    val live: ZLayer[Any, Nothing, EntitiesService] = ???
}
