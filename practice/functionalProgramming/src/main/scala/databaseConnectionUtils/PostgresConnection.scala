package databaseConnectionUtils

import java.sql.{Connection, DriverManager, ResultSet, Statement}
import scala.util.Try

object PostgresConnection {
    private val url = "jdbc:postgresql://localhost:5432/simple_db"
    private val user = "postgres"
    private val password = "password"

    def createConnection: Try[Connection] = Try(DriverManager.getConnection(url, user, password))

    def createStatement: Try[Statement] = createConnection.flatMap(con => Try(con.createStatement()))

    def executeQuery(sqlString: String): Try[ResultSet] = createStatement.flatMap(st => Try(st.executeQuery(sqlString)))

    def executeUpdate(sqlString: String): Try[Int] = createStatement.flatMap(st => Try(st.executeUpdate(sqlString)))
}
