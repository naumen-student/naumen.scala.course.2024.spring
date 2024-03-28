package src.main.scala
import scala.collection.mutable
trait Cell {
  def toString(): String
}


class EmptyCell extends Cell {
  override def toString(): String = "empty"
}


class NumberCell(number: Int) extends Cell {
  override def toString(): String = number.toString
}


class StringCell(text: String) extends Cell {
  override def toString(): String = text
}


class ReferenceCell(ix: Int, iy: Int, table: Table) extends Cell {
  override def toString(): String = {
    if (ix < 0 || iy < 0 || ix >= table.width || iy >= table.height)
      "outOfRange"
    else if (table.isCyclicReference(ix, iy))
      "cyclic"
    else
      table.getCell(ix, iy) match {
        case Some(cell) => cell.toString()
        case None       => "empty"
      }
  }
}
