import scala.annotation.tailrec

trait Cell {
  def toString(): String
}

case class EmptyCell() extends Cell {
  override def toString(): String = "empty"
}

case class  NumberCell(value: Int) extends Cell {
  override def toString(): String = this.value.toString
}

case class StringCell(value: String) extends Cell {
  override def toString(): String = value
}

case class ReferenceCell(ix: Int, iy: Int, table: Table) extends Cell {
  @tailrec
  private def toStringImpl(history: Set[ReferenceCell] = Set.empty): String = table.getCell(ix, iy) match {
    case Some(refCell: ReferenceCell) =>
      if (history.contains(this)) "cyclic"
      else refCell.toStringImpl(history + refCell)
    case Some(cell: Cell) => cell.toString
    case None => "outOfRange"
  }

  override def toString(): String = toStringImpl()
}
