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
  override def toString: String = {
    table.getCell(ix, iy).map {
      case referenceCell: ReferenceCell => referenceCell.toString
      case cell: Cell => cell.toString
    }getOrElse("outOfRange")
  }
}