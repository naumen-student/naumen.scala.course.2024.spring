package entities.sqlGenerationTools.typeClasses

import entities.{Person, User}

import java.sql.ResultSet

trait FromDBConverter[T] {
    def dbObjToScalaObj(dbObj: ResultSet): T
}

object FromDBConverters {
    implicit val userFromDBConverter: FromDBConverter[User] = (dbObj: ResultSet) => User(
        id = dbObj.getInt("id"), login = dbObj.getString("login"), password = dbObj.getString("password")
    )

    implicit val personFromDBConverter: FromDBConverter[Person] = (dbObj: ResultSet) => Person(
        id = dbObj.getInt("id"),
        userId = dbObj.getInt("userId"),
        fio = dbObj.getString("fio"),
        birthDate = dbObj.getTimestamp("birthDate")
    )
}
