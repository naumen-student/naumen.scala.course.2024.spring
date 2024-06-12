trait Cell {
  def toString: String
}

case class EmptyCell() extends Cell {
  override def toString = "empty"
}

case class NumberCell(num: Int) extends Cell {
  override def toString = num.toString
}

case class StringCell(str: String) extends Cell {
  override def toString: String = str
}
case class ReferenceCell(ix: Int, iy: Int, table: Table) extends Cell {
  private def toStringImpl(refCellsHistory: Set[ReferenceCell] = Set.empty):
    String = table.getCell(ix, iy) match {
    case Some(_: ReferenceCell) if refCellsHistory.contains(this) => "cyclic"
    case Some(refCell: ReferenceCell) => refCell.toStringImpl(refCellsHistory + refCell)
    case Some(cell) => cell.toString
    case None => "outOfRange"
  }
  override def toString: String = toStringImpl()
}
