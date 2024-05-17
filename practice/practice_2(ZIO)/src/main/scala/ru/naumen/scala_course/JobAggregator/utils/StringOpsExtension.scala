package ru.naumen.scala_course.JobAggregator.utils

trait StringOpsExtension {

  def nonEmptySafe(s: String) = Option(s).exists(_.trim.nonEmpty)
  def nonEmptySafe(os: Option[String]) = os.exists(_.trim.nonEmpty)
  def isEmptySafe(s: String) = !nonEmptySafe(s)
  def toStringSafe(o: Any) = Option(o).map(_.toString)
  def trimToOption(s: String): Option[String] = trimToOption(Option(s))
  def trimToOption(o: Any): Option[String] = trimToOption(toStringSafe(o))
  def trimToOption(opt: Option[String]): Option[String] = opt.map(_.trim).filter(_.nonEmpty)
  def takeSafe(s: String, l: Int) = trimToOption(s).map(_.take(l))
  def stripToEmpty(s: String) = if (s == null) "" else s
  def trimSafe(s: String) = stripToEmpty(s).trim


  trait Labeled
  {
    def label: String
  }


  def coordinateWithNumeral(k: Int, form1: String, form2: String, form5: String) =
  {
    (Math.abs(k) % 100, Math.abs(k) % 10) match
    {
      case (n,_) if n > 10 && n < 20 => form5
      case (_,n) if n > 1 && n < 5 => form2
      case (_,1) => form1
      case (_,_) => form5
    }
  }
  def levenshteinDistance(s1: String, s2: String): Int = {

    def minimum(i1: Int, i2: Int, i3: Int): Int = math.min(math.min(i1, i2), i3)

    val dist = Array.tabulate(s2.length+1, s1.length+1){ (j,i) => if (j==0) i else if (i==0) j else 0 }

    for(j<-1 to s2.length; i<-1 to s1.length)
      dist(j)(i) = if(s2(j-1) == s1(i-1))
        dist(j-1)(i-1)
      else
        minimum(dist(j-1)(i) + 1, dist(j)(i-1) + 1, dist(j-1)(i-1) + 1)

    dist(s2.length)(s1.length)
  }

  /**
   * Выводит форму слова, подходящую под число.
   * Например:
   * 1 год, 2 года, 25 лет
   * @param qty n исходное число
   * @param form1 форма слова под единицу
   * @param form2 форма слова под 2-3
   * @param form3 форма слова под 5 и более
   */
  def wordFormDependsOnQty(form1: String, form2: String, form3: String)(qty: Int): String = {
    val num = Math.abs(qty) % 100
    val num2 = num % 10

    if (num > 10 && num < 20) form3
    else if (num2 > 1 && num2 < 5) form2
    else if (num2 == 1) form1
    else form3
  }

  def cyrillicToLatin(text: String): String = {
    val cyrillicLatinMap: Map[Char, String] = Map('а' -> "a", 'б' -> "b", 'в' -> "v", 'г' -> "g", 'д' -> "d", 'е' -> "e",
      'ё' -> "e", 'ж' -> "zh", 'з' -> "z", 'и' -> "i", 'й' -> "y", 'к' -> "k", 'л' -> "l", 'м' -> "m", 'н' -> "n",
      'о' -> "o", 'п' -> "p", 'р' -> "r", 'с' -> "s", 'т' -> "t", 'у' -> "u", 'ф' -> "f", 'х' -> "h", 'ц' -> "tc",
      'ч' -> "ch", 'ш' -> "sh", 'щ' -> "sch", 'ы' -> "ii", 'э' -> "e", 'ю' -> "yu", 'я' -> "ya",  '0' -> "0", '1' -> "1",
      '2' -> "2", '3' -> "3", '4' -> "4", '5' -> "5", '6' -> "6", '7' -> "7", '8' -> "8", '9' -> "9", ' ' -> " ",
      'a' -> "a", 'b' -> "b", 'c' -> "c", 'd' -> "d", 'e' -> "e", 'f' -> "f", 'g' -> "g", 'h' -> "h", 'i' -> "i", 'g' -> "g",
      'k' -> "k", 'l' -> "l", 'm' -> "m", 'n' -> "n", 'o' -> "o", 'p' -> "p", 'q' -> "q", 'r' -> "r", 's' -> "s", 't' -> "t",
      'u' -> "u", 'v' -> "v", 'w' -> "w", 'x' -> "x", 'y' -> "y", 'z' -> "z")
    text.map(ch => cyrillicLatinMap.getOrElse(ch, "")).mkString
  }
}

object StringOpsExtension extends StringOpsExtension