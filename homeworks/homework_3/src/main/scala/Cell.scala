import scala.annotation.tailrec
import scala.collection.mutable

trait Cell {

}

class EmptyCell() extends Cell {
  override def toString: String = "empty"
}

class NumberCell(number: Int) extends Cell {
  override def toString: String = number.toString
}

class ReferenceCell(ix: Int, iy: Int, table: Table) extends Cell {
  override def toString: String = {
    findString()
  }

  @tailrec
  private def findString(visited: mutable.Set[Cell] = mutable.Set.empty): String = {
    table.getCell(ix, iy) match {
      case Some(cell: ReferenceCell) => if (visited.contains(cell)) "cyclic" else cell.findString(visited += cell)
      case Some(cell: Cell) => cell.toString
      case None => "outOfRange"
    }
  }
}

class StringCell(string: String) extends Cell {
  override def toString: String = string
}
