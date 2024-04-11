/**
 * Класс таблицы
 *
 * @param width  Ширина
 * @param height Длина
 */
class Table(width: Int, height: Int) {
  /**
   * Ячейки размером [[width]] x [[height]], выраженные одномерным массивом. По-умолчанию все ячейки пустые
   */
  private val cells: Array[Cell] = Array.fill(width * height)(new EmptyCell)

  /**
   * Возвращает ячейку по индексам строки и столбца, либо "None", если ix или iy вне границ таблицы
   *
   * @param ix Индекс строки
   * @param iy Индекс столбца
   */
  def getCell(ix: Int, iy: Int): Option[Cell] = {
    if (ix >= 0 && ix < width && iy >= 0 && iy < height) {
      Some(cells(ix * width + iy))
    } else {
      None
    }
  }

  /**
   * Устанавливает ячейку по индексам строки и столбца
   *
   * @param ix   Индекс строки
   * @param iy   Индекс столбца
   * @param cell Ячейка
   */
  def setCell(ix: Int, iy: Int, cell: Cell): Unit = {
    if (ix >= 0 && ix < width && iy >= 0 && iy < height) {
      cells(ix * width + iy) = cell
    }
  }
}