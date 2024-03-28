class Table(x: Int, y: Int) {
  private val cells: Array[Cell] = Array.fill[Cell](x * y){ new EmptyCell }

  private def inRange(ix: Int, iy: Int): Boolean = ix > -1  && ix < x && iy > -1 && iy < y

  def getCell(ix: Int, iy: Int): Option[Cell] = if (inRange(ix, iy)) Option(cells(ix + iy * y)) else None

  def setCell(ix: Int, iy: Int, cell: Cell): Unit = {
    if (ix > -1  && ix < x && iy > -1 && iy < y) cells(ix + iy * y) = cell
  }
}