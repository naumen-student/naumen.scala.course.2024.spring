class Table(val height: Int, val width: Int) {
  private val cells = Array.fill[Cell](height * width)(new EmptyCell);

  def inRange(row: Int, column: Int): Option[Boolean] =
    Some(row >= 0 && row < height && column >= 0 && column < width)
      .filter(res => res)
  def getCell(row: Int, column: Int): Option[Cell] = inRange(row, column).map(_ => cells(row * width + column))

  def setCell(row: Int, column: Int, cell: Cell): Unit = {
    inRange(row, column)
      .foreach(_ => cells(row * width + column) = cell)
  }
}