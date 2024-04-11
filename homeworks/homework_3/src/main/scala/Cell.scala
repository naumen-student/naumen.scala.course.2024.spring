/**
 * Табличная ячейка
 */
trait Cell {

}

/**
 * Пустая ячейка
 */
class EmptyCell extends Cell {
  override def toString: String = "empty"
}

/**
 * Ячейка с числом
 *
 * @param value Значение
 */
class NumberCell(val value: Int) extends Cell {
  override def toString: String = value.toString
}

/**
 * Ячейка с текстом
 *
 * @param value Значение
 */
class StringCell(val value: String) extends Cell {
  override def toString: String = value
}

/**
 * Ячейка, содержащая ссылку на другую ячейку
 * toString возвращает значение ячейки, на которую ссылается ссылка, или "outOfRange", если ссылка ведет за пределы
 * таблицы, или "cyclic" если циклическая ссылка
 *
 * @param ix    Индекс строки
 * @param iy    Индекс столбца
 * @param table Таблица
 */
class ReferenceCell(val ix: Int, val iy: Int, val table: Table) extends Cell {
  override def toString: String = {
    table.getCell(ix, iy) match {
      case Some(cell) => cell match {
        case _: ReferenceCell => toRefValueOrCyclic(ix, iy, table, cell.asInstanceOf[ReferenceCell])
        case _ => cell.toString
      }
      case None => "outOfRange"
    }
  }

  /**
   * Возвращает значение ячейки, на которую ссылается ячейка с ссылкой, или "cyclic" если циклической ссылка, или
   * "outOfRange" если ссылка ведет к выходу за границы таблицы
   *
   * @param ix    Индекс строки ячейки с ссылкой
   * @param iy    Индекс столбца ячейки с ссылкой
   * @param table Таблица, в которой находится ячейки с ссылкой
   * @param cell  Ячейка с ссылкой
   */
  private def toRefValueOrCyclic(ix: Int, iy: Int, table: Table, cell: ReferenceCell): String = {
    var currentCell: Cell = cell

    while (currentCell.isInstanceOf[ReferenceCell]) {
      val currentRefCell = currentCell.asInstanceOf[ReferenceCell]
      if (currentRefCell.ix == ix && currentRefCell.iy == iy) {
        return "cyclic"
      }
      table.getCell(currentRefCell.ix, currentRefCell.iy) match {
        case Some(cell) => currentCell = cell
        case None => return "outOfRange"
      }
    }

    currentCell.toString
  }
}