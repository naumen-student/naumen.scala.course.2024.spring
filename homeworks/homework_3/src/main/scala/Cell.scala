import scala.annotation.tailrec

trait Cell {
  def toString(): String
}

class EmptyCell extends Cell {
  override def toString(): String = "empty"
}

class NumberCell(number: Int) extends Cell {
  override def toString(): String = number.toString
}

class StringCell(string: String) extends Cell {
  override def toString(): String = string
}

class ReferenceCell(ix: Int, iy: Int, table: Table) extends Cell {
  private def getRefCell: Option[Cell] = table.getCell(ix, iy)

  @tailrec
  private def getRefCellString(visited: Set[Cell] = Set.empty): String = {
    getRefCell match {
      case Some(cell: ReferenceCell) => if (visited.contains(cell)) "cyclic" else cell.getRefCellString(visited + cell)
      case Some(cell: Cell) => cell.toString
      case None => "outOfRange"
    }
  }

  override def toString(): String = getRefCellString()
}