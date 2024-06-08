import databaseConnectionUtils.DatabaseService
import entities._
import entities.sqlGenerationTools.typeClasses.FromDBConverters._
import entities.sqlGenerationTools.typeClasses.ValuesToSqlFilters._

object Main extends App {

    lazy val query =
        for {
            user <- DatabaseService.select[UserId, User](UserId(1))
            deletedCount <- DatabaseService.delete[UserId, Person](UserId(user.head.id))
        } yield deletedCount

    query.fold(
        res => println("Query is successful with result " + res),
        error => println("ERROR \n" + error)
    )

}
