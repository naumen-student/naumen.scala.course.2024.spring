trait Cell

class EmptyCell extends Cell {
  override def toString: String = "empty"
}

class NumberCell(value: Int) extends Cell {
  override def toString: String = value.toString
}

class StringCell(value: String) extends Cell {
  override def toString: String = value
}

class ReferenceCell(ix: Int, iy: Int, table: Table) extends Cell {
  private def toStringImpl(prev: Option[ReferenceCell] = None): String = table.getCell(ix, iy) match {
    case Some(_: ReferenceCell) if prev.isDefined && prev.get == this => "cyclic"
    case Some(refCell: ReferenceCell) => refCell.toStringImpl(Some(refCell))
    case Some(cell) => cell.toString
    case None => "outOfRange"
  }

  override def toString: String = toStringImpl()
}
