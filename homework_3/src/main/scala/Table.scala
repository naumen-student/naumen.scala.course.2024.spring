import scala.collection.mutable.HashMap


class Table(private val width: Int, private val height: Int) {

    private val tableData: HashMap[String, Cell] = new HashMap()

    def getCell(x: Int, y: Int): Option[String] = {

        if (checkCell(x, y)) {
            return Option.empty
        }

        if (!tableData.keySet.contains(getKey(x, y))) {
            return Option("empty")
        }

        Option(this.tableData(getKey(x, y)).toString)
    }

    def setCell(x: Int, y: Int, cell: Cell): Unit = {

        if (checkCell(x, y)) {
            return
        }

        this.tableData.put(getKey(x, y), cell)
    }

    def getCellValue(x: Int, y: Int): Cell = {
        if (checkCell(x, y) || !tableData.keySet.contains(getKey(x, y))) {
            return null
        }

        this.tableData(getKey(x, y))
    }

    private def checkCell(x: Int, y: Int): Boolean = {
        x < 0 || y < 0 || x > width || y > height
    }

    private def getKey(x: Int, y: Int): String = {
        s"${x}-${y}"
    }
}