import scala.collection.mutable.ListBuffer

class Table(width: Int, height: Int) {
  private val cells : ListBuffer[Cell] = ListBuffer.fill[Cell](height * width )(new EmptyCell)

  def getCell(ix: Int, iy: Int): Option[Cell] = {
    if (ix >= 0 && ix < width && iy >= 0 && iy < height) {
      Some(cells(width * iy + ix))
    }
    else None
  }

  def setCell(ix: Int, iy: Int, cell: Cell): Unit = {
    cells(ix + width * iy) = cell
  }
}