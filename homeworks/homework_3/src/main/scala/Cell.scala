trait Cell {}

class EmptyCell extends Cell {
  override def toString: String = "empty"
}

class NumberCell(num: Int) extends Cell {
  override def toString: String = num.toString
}

class StringCell(string: String) extends Cell {
  override def toString: String = string
}

class ReferenceCell(ix: Int, iy: Int, table: Table) extends Cell {
  private def tryGetNextCell: Option[Cell] = table.getCell(ix, iy)

  override def toString: String = {
    def isCyclic(cell: Cell, visited: Set[Cell]): Boolean = cell match {
      case refCell: ReferenceCell =>
        if (visited.contains(refCell)) true
        else refCell.tryGetNextCell.exists(nextCell => isCyclic(nextCell, visited + refCell))
      case _ => false
    }

    table.getCell(ix, iy) match {
      case Some(cell: ReferenceCell) => if (isCyclic(cell, Set(this))) "cyclic" else cell.toString
      case Some(cell: Cell) => cell.toString
      case None => "outOfRange"
    }
  }
}