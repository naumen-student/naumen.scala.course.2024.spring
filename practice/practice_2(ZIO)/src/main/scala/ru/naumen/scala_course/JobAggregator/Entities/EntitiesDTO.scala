package ru.naumen.scala_course.JobAggregator.Entities

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import sttp.tapir.Schema

case class EntitiesDTO(id: String, name: String)

object EntitiesDTO {
    implicit val codec: Codec[EntitiesDTO] = deriveCodec
    implicit val schema: Schema[EntitiesDTO] = Schema.derived
}
