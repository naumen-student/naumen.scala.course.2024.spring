

abstract class Cell {
}

class EmptyCell() extends Cell {

    override def toString(): String = "empty"
}

class NumberCell(private val value: Int) extends Cell {

    override def toString(): String = value.toString
}

class StringCell(private val value: String) extends Cell {

    override def toString: String = value;
}

class ReferenceCell(val x: Int, val y: Int, private val table: Table) extends Cell {

    override def toString: String = {
        val referenceCell = this.table.getCellValue(x, y)
        if (isCyclic(referenceCell)) {
            return "cyclic"
        }

        val cell = this.table.getCell(x, y)
        if (cell.isEmpty) {
            return "outOfRange"
        }

        cell.get
    }

    def isCyclic(cell: Cell): Boolean = {
        if (!cell.isInstanceOf[ReferenceCell])
            return false

        val referenceCell = cell.asInstanceOf[ReferenceCell]
        val referenceRefCell = this.table.getCellValue(referenceCell.x, referenceCell.y)

        if (!referenceRefCell.isInstanceOf[ReferenceCell])
            return false

        val referenceRefCell2 = referenceRefCell.asInstanceOf[ReferenceCell]
        this.x == referenceRefCell2.x && this.y == referenceRefCell2.y
    }
}