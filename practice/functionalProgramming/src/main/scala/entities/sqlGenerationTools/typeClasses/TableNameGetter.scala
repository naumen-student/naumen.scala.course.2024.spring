package entities.sqlGenerationTools.typeClasses

import entities.{Person, User}

trait TableNameGetter[T] {
    val name: String
}

object TableNameGetter {
    implicit val userTableName: TableNameGetter[User] = new TableNameGetter[User] {
        override val name: String = """"User""""
    }

    implicit val personTableName: TableNameGetter[Person] = new TableNameGetter[Person] {
        override val name: String = """"Person""""
    }

}
