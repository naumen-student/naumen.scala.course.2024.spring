package ru.naumen.scala_course.JobAggregator.Entities.dao.entities

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import ru.naumen.scala_course.JobAggregator.utils.UuidGenerator
import sttp.tapir
import sttp.tapir.Codec.PlainCodec

import java.time.{LocalDate, LocalDateTime}

case class Entities(
                 id: String,
                 name: String
               ){
  def typedId: EntitiesId = EntitiesId(id)
}

case class EntitiesId(id: String) extends AnyVal

object EntitiesId{
  implicit val codec: PlainCodec[EntitiesId] =
    tapir.Codec.string.map(value => EntitiesId(value))(s => s.id)

  implicit val codecJson: Codec[EntitiesId] = deriveCodec
}

object Entities {
    implicit val codecJson: Codec[Entities] = deriveCodec
}
