class Table(width: Int, height: Int){
  private val tableArray:  Array[Cell] = Array.fill[Cell](width * height)(new EmptyCell)
  private def inRange(ix: Int, iy: Int) = {
    Some(ix > -1 && iy > -1 && ix < width && iy < height)
      .filter(x => x)
  }
  def getCell(ix: Int, iy: Int): Option[Cell] = inRange(ix, iy)
    .map(_ => tableArray(idx(ix, iy)))

  private def idx(ix: Int, iy: Int) = {
    iy + ix * height
  }

  def setCell(ix: Int, iy: Int, cell: Cell): Unit = inRange(ix, iy)
    .foreach(_ => tableArray(idx(ix, iy)) = cell)
}