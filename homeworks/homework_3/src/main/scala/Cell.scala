trait Cell

class EmptyCell extends Cell {
  override def toString: String = "empty"
}
class NumberCell (var content: Int) extends Cell  {
  override def toString: String = content.toString
}
class StringCell (var content: String ) extends Cell {
  override def toString: String = content
}
class ReferenceCell (var row: Int, var column: Int, var table: Table) extends Cell {

  def toStringRef(history: Set[ReferenceCell] = Set.empty): String =
    table.getCell(row, column)
      .map(cell => cell match {
        case ref: ReferenceCell => Some(history.contains(this))
          .filter(res => res)
          .map(_ => "cyclic")
          .getOrElse(ref.toStringRef(history ++ Set(ref)))
        case cell: Cell => cell.toString
      }).getOrElse("outOfRange")


  override def toString: String = toStringRef()
}