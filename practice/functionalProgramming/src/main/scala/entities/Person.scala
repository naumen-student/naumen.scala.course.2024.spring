package entities

import java.sql.Timestamp

case class Person(id: Int, userId: Int, fio: String, birthDate: Timestamp)

case class PersonId(raw: Int)