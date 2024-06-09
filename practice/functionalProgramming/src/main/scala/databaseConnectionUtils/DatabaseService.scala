package databaseConnectionUtils

import entities.sqlGenerationTools.SqlGenerator
import entities.sqlGenerationTools.typeClasses.{FromDBConverter, TableNameGetter, ToDBConverter, ValuesToSqlFilter}

import java.sql.ResultSet
import scala.collection.mutable.ArrayBuffer

object DatabaseService {

    def insert[T](obj: T)(implicit tableNameGetter: TableNameGetter[T], toDBConverter: ToDBConverter[T]): QueryResult[Boolean] = {
        val sql = SqlGenerator.genInsert(obj)
        PostgresConnection.executeQuery(sql)
            .fold(
                err => FailureResult.from[Boolean](err, sql),
                res => SuccessResult(result = res.next())
            )
    }

    private def performResultList[T](result: ResultSet)(implicit converter: FromDBConverter[T]) = {
        val buffer = new ArrayBuffer[T]()
        while (result.next()) {
            val obj = converter.dbObjToScalaObj(result)
            buffer.addOne(obj)
        }
        buffer.toList
    }

    def select[F, T](filterVal: F)(implicit tableNameGetter: TableNameGetter[T], valuesToSqlFilter: ValuesToSqlFilter[F, T], converter: FromDBConverter[T]): QueryResult[List[T]] = {
        val sql = SqlGenerator.genSelect(filterVal)
        PostgresConnection.executeQuery(sql)
            .fold(
                err => FailureResult.from(err, sql),
                res => SuccessResult(performResultList(res))
            )
    }

    def delete[F, T](filter: F)(implicit tableNameGetter: TableNameGetter[T], valuesToSqlFilter: ValuesToSqlFilter[F, T]): QueryResult[Int] = {
        val sql = SqlGenerator.genDelete(filter)
        PostgresConnection.executeUpdate(sql)
            .fold(
                err => FailureResult.from(err, sql),
                res => SuccessResult(res)
            )
    }

}
