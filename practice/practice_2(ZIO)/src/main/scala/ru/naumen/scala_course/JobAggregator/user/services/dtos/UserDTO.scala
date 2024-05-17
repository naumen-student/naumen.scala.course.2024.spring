package ru.naumen.scala_course.JobAggregator.user.services.dtos

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import ru.naumen.scala_course.JobAggregator.user.dao.entities.User

case class UserDTO(
                    id: String,
                    firstName: String,
                    lastName: String,
                    age: Int
                  )

object UserDTO {
    implicit val codec: Codec[UserDTO] = deriveCodec

    def from(user: User) =
        UserDTO(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            age = user.age
        )
}
