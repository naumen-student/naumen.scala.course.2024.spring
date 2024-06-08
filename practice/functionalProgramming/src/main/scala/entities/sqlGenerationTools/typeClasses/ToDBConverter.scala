package entities.sqlGenerationTools.typeClasses

import entities.{Person, User}

trait ToDBConverter[T] {
    def getObjDescription(obj: T): String

    def getValue(obj: T): String
}

object ToDBConverters {
     implicit val userToDBConverter: ToDBConverter[User] = new ToDBConverter[User] {
        override def getObjDescription(obj: User): String =
            "(id, login, password)"

        override def getValue(obj: User): String =
            s"(${obj.id}, '${obj.login}', '${obj.password}')"
    }

    implicit val personToDBConverter: ToDBConverter[Person] = new ToDBConverter[Person] {
        override def getObjDescription(obj: Person): String =
            """(id, "userId", fio, "birthDate")"""

        override def getValue(obj: Person): String =
            s"(${obj.id}, '${obj.userId}', '${obj.fio}', '${obj.birthDate}')"
    }
}