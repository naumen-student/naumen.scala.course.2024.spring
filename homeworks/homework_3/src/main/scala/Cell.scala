import scala.collection.mutable

trait Cell {}

class EmptyCell extends Cell {
  override def toString: String = "empty"
}

class NumberCell(num: Int) extends Cell {
  override def toString: String = num.toString
}

class StringCell(str: String) extends Cell {
  override def toString: String = str
}

class ReferenceCell(ix: Int, iy: Int, table: Table) extends Cell {

  private def getRef(visited: mutable.Set[ReferenceCell]): Option[Cell] = {
    table.getCell(ix, iy) match {
      case Some(cell: ReferenceCell) => {
        if (!visited.contains(cell)) {
          visited.add(cell)
          cell.getRef(visited)
        }
        else None
      }
      case Some(cell: Cell) => Some(cell)
      case None => None
    }
  }

  override def toString: String = {
    val ref = getRef(mutable.Set.empty[ReferenceCell])

    ref match {
      case None => "cyclic"
      case Some(cell: Cell) => cell.toString
    }
  }
}