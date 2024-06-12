class Table(width: Int, length: Int) {
  private val cells: Array[Array[Cell]] = Array.fill(width, length)(new EmptyCell)

  def getCell(ix: Int, iy: Int): Option[Cell] = {
    if (ix < 0 || iy < 0 || ix > width || iy > length)
      None
    else Some(cells(ix)(iy))
  }

  def setCell(ix: Int, iy: Int, cell: Cell): Unit = {
    if (!(ix < 0 || iy < 0 || ix > width || iy > length))
      cells(ix)(iy) = cell
  }
}
