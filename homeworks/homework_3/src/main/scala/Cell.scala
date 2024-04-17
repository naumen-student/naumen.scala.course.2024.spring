import scala.compiletime.ops.int

abstract class Cell {
}

class EmptyCell() extends Cell {

    override def toString(): String = "empty"
}

class NumberCell(private val value: Int) extends Cell {

    override def toString(): String = value
}

class StringCell(private val value: String) extends Cell {

    override def toString: String = value;
}

class ReferenceCell(private val x: Int, private val y: Int, private val table: Table) extends Cell {

    override def toString: String = {
        val cell: AnyRef = this.table.getCell(x, y).get

        if (cell == None) {
            return "outOfRange"
        }

        if (cell.isInstanceOf[ReferenceCell]) {
            return "cyclic"
        }

        cell.toString
    }
}