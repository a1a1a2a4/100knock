package 100knock.ishida.01

object App {
  def reverseString(str: String): String = str.reverse
  def oddCharsToString(str: String): String = str.zipWithIndex filter { case (s, i) => if (i % 2 == 0) true else false} map { case (s, i) => s } mkString
  def interlock(str1: String, str2: String): String = {
    def interlockToCharList(str1: String, str2: String): List[Char] = str1 match {
      case s if str1.size > 0 => str1.head :: str2.head :: Nil ++ interlockToCharList(str1.tail, str2.tail)
      case _ => Nil
    }
    interlockToCharList(str1, str2).mkString
  }
  def getStringSizes(str: String): List = str.replaceAll("[,|.]", "") split "\\s+" map { _.size } toList
  def zipElementWithIndex(str: String): Map[String, Int] = {
    val oneCharacterSet = Set(1, 5, 6, 7, 8, 9, 15, 16, 19)
    str.replaceAll("[,|.]", "").split("\\s+").zipWithIndex map {
      case (s, i) if oneCharacterSet contains i => (s.head.toString, i+1)
      case (s, i) => (s.slice(0, 2), i+1)
    } toMap
  }
def nGram(str: String, n: Int): Map[String, Int] = {
  def splitToListByN(str: String, n: Int): List[String] = str match {
    case str if str.size > 0 => {
      val (head, tail) = str.replaceAll("\\s+", "") splitAt n
      List(head) ++ splitToListByN(tail, n)
    }
    case _ => Nil
  }
  val splited = splitToListByN(str, n)
  splited map {s => (s, )}
}
}
