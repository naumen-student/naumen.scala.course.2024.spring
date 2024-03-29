class Table(width: Int, height: Int) {
  private val cells: Array[Cell[Any]] = Array.fill[Cell[Any]](width * height)(new EmptyCell)
  private def idx (ix: Int, iy: Int): Int =  ix + iy * width

  def getCell(ix: Int, iy: Int): Option[Cell[Any]] = {
    isInRange(ix, iy)
      .map(_ => cells(idx(ix, iy)))
  }

  def setCell(ix: Int, iy: Int, cell: Cell[Any]): Unit = {
    isInRange(ix, iy)
      .filter(res => res)
      .foreach(_ => cells(idx(ix, iy)) = cell)
  }

  private def isInRange(ix: Int, iy: Int): Option[Boolean] = {
    Some(ix >= 0 && ix < width && iy >= 0 && iy < height).filter(res => res)
  }
}