error id: file://<WORKSPACE>/homeworks/homework_3/src/main/scala/Table.scala:[483..484) in Input.VirtualFile("file://<WORKSPACE>/homeworks/homework_3/src/main/scala/Table.scala", "import java.util.HashMap
import scala.compiletime.ops.int


class Table(private val width: Int, private val height: Int) {

    private final val tableData: Map[String, Cell] = new HashMap();

    def getCell(x: Int, y: Int): Option[Cell] = {
        
        if (!checkCell(x, y)) {
            return "None"
        }

        tableData
    }

    private def checkCell(x: int, y: int): Boolean = {
        return x < 0 || y < 0 || x > width || y > height 
    }

    private def 
}")
file://<WORKSPACE>/homeworks/homework_3/src/main/scala/Table.scala
file://<WORKSPACE>/homeworks/homework_3/src/main/scala/Table.scala:23: error: expected identifier; obtained rbrace
}
^
#### Short summary: 

expected identifier; obtained rbrace