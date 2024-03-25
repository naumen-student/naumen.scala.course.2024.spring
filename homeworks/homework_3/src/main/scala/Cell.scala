package src.main.scala

import scala.collection.mutable


trait Cell {
  def toString(): String
}

class EmptyCell extends Cell {
  override def toString(): String = "empty"
}

class NumberCell(val num: Int) extends Cell {
  override def toString(): String = num.toString
}

class StringCell(val string: String) extends Cell {
  override def toString(): String = string
}

class ReferenceCell(val ix: Int, val iy: Int, table: Table) extends Cell {
  private def refToString(setVisited: mutable.Set[Cell] = mutable.Set.empty): String = {
    table.getCell(ix, iy) match {
      case Some(cell: ReferenceCell) => if (setVisited.contains(cell)) "cyclic" else cell.refToString(setVisited += cell)
      case Some(cell: Cell) => cell.toString
      case None => "outOfRange"
    }
  }

  override def toString(): String = refToString()
}