package entities.sqlGenerationTools.typeClasses

import entities.{Person, PersonId, User, UserId}

trait ValuesToSqlFilter[F, T] {
    def getSqlFilterStr(filterObj: F): String
}

object ValuesToSqlFilters {
    implicit val filterUserById: ValuesToSqlFilter[UserId, User] = (id: UserId) => s"id = ${id.raw}"

    implicit val filterPersonByUserId: ValuesToSqlFilter[UserId, Person] = (userId: UserId) => s""""userId" = ${userId.raw}"""
}
