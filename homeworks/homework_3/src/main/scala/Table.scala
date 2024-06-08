import scala.collection.mutable.HashMap
import scala.compiletime.ops.int
import scala.compiletime.ops.string


class Table(private val width: Int, private val height: Int) {

    private val tableData: HashMap[String, Cell] = HashMap()

    def getCell(x: Int, y: Int): Option[AnyRef] = {
        
        if (!checkCell(x, y)) {
            return Option("None")
        }

        this.tableData.get(getKey(x, y))
    }

    def setCell(x: Int, y: Int, cell: Cell): Unit = {

        if (!checkCell(x, y)) {
            return
        }

        this.tableData.addOne((getKey(x, y), cell))
    }

    private def checkCell(x: Int, y: Int): Boolean = {
        x < 0 || y < 0 || x > width || y > height 
    }

    private def getKey(x: Int, y: Int): String = {
        s"${x}-${y}"
    }
}