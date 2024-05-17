trait Cell {}

class EmptyCell() extends Cell {
  override def toString: String = "empty"
}

class NumberCell(num: Int) extends Cell {
  override def toString: String = num.toString
}

class StringCell(str: String) extends Cell {
  override def toString: String = str
}

class ReferenceCell(ix: Int, iy: Int, table: Table) extends Cell {

  private def getRef: Option[Cell] = table.getCell(ix, iy)

  private def toStringImpl(cellHistory: Set[ReferenceCell] = Set.empty): String = {
    table.getCell(ix, iy).map {
      case referenceCell: ReferenceCell => Some(cellHistory.contains(this)).filter(cell => cell).map(_ =>
        "cyclic").getOrElse(referenceCell.toStringImpl(cellHistory ++ Set(referenceCell)))
      case cell: Cell => cell.toString
    } getOrElse ("outOfRange")
  }

  override def toString: String = {
    toStringImpl()
  }

}