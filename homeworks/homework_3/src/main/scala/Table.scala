package src.main.scala
import scala.collection.mutable


class Table(val width: Int, val height: Int) {
  private val cells: mutable.ArrayBuffer[Cell] = mutable.ArrayBuffer.fill(width * height)(new EmptyCell)


  def getCell(ix: Int, iy: Int): Option[Cell] = {
    if (ix < 0 || iy < 0 || ix >= width || iy >= height) None
    else Some(cells(ix + iy * width))
  }


  def setCell(ix: Int, iy: Int, cell: Cell): Unit = {
    if (ix >= 0 && iy >= 0 && ix < width && iy < height) {
      cells(ix + iy * width) = cell
    }
  }


  def isCyclicReference(ix: Int, iy: Int, visited: Set[(Int, Int)] = Set()): Boolean = {
    if (visited.contains((ix, iy))) true
    else {
      getCell(ix, iy) match {
        case Some(cell: ReferenceCell) =>
          isCyclicReference(cell.ix, cell.iy, visited + ((ix, iy)))
        case _ => false
      }
    }
  }
}
