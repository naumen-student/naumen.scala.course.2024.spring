package entities.sqlGenerationTools

import entities.sqlGenerationTools.typeClasses.{TableNameGetter, ToDBConverter, ValuesToSqlFilter}

object SqlGenerator {

    def genInsert[T](obj: T)(implicit tableNameGetter: TableNameGetter[T], toDBConverter: ToDBConverter[T]): String =
        s"""INSERT INTO ${tableNameGetter.name} ${toDBConverter.getObjDescription(obj)} VALUES ${toDBConverter.getValue(obj)};"""

    def genSelect[F, T](filters: F)(implicit tableNameGetter: TableNameGetter[T], valuesToSqlFilter: ValuesToSqlFilter[F, T]): String =
        s"""SELECT * FROM ${tableNameGetter.name} WHERE ${valuesToSqlFilter.getSqlFilterStr(filters)};"""

    def genDelete[F, T](filters: F)(implicit tableNameGetter: TableNameGetter[T], valuesToSqlFilter: ValuesToSqlFilter[F, T]): String =
        s"""DELETE FROM ${tableNameGetter.name} WHERE ${valuesToSqlFilter.getSqlFilterStr(filters)};"""
}
