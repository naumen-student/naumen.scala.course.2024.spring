class Table(width: Int, height: Int) {
  private val cells: Array[Cell] = Array.fill[Cell](width * height)(EmptyCell())
  private def isInRange(ix: Int, iy: Int): Boolean = ix >= 0 && ix < width && iy >= 0 && iy < height
  private def index(ix: Int, iy: Int): Int = ix + iy * width
  def getCell(ix: Int, iy: Int): Option[Cell] = if (isInRange(ix, iy)) Some(cells(index(ix, iy))) else None
  def setCell(ix: Int, iy: Int, cell: Cell): Unit = if (isInRange(ix, iy)) cells(index(ix, iy)) = cell
}