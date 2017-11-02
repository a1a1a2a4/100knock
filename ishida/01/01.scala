package oneHundredKnock.ishida

object App {

  def main(args: Array[String]): Unit = {
    
  }

  def reverseString(str: String): String = str.reverse

  def oddCharsStringoString(str: String): String = str.zipWithIndex filter { case (s, i) => if (i % 2 == 0) true else false} map { case (s, i) => s } mkString

  def interlock(str1: String, str2: String): String = {
    def interlockToCharList(str1: String, str2: String): List[Char] = str1 match {
      case s if str1.size > 0 => str1.head :: str2.head :: Nil ++ interlockToCharList(str1.tail, str2.tail)
      case _ => Nil
    }
    interlockToCharList(str1, str2).mkString
  }

  def getStringSizes(str: String): List[Int] = str.replaceAll("[,|.]", "") split "\\s+" map { _.size } toList

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
    splitToListByN(str, n) groupBy identity mapValues {s => s.size}
  }

  def biGramSet(str1: String, str2: String) = {
    val X = nGram(str1, 2)
    val Y = nGram(str2, 2)

    def diffMap(x: Map[String, Int], y: Map[String, Int]): Map[String, Int] = x -- y.keySet
    def andMap(x: Map[String, Int], y: Map[String, Int]): Map[String, Int] = x.keySet & y.keySet map (k => (k, x(k) + y(k))) toMap
    def orMap(x: Map[String, Int], y: Map[String, Int]): Map[String, Int] = diffMap(x, y) ++ diffMap(y, x) ++ andMap(x, y)

    println(diffMap(X, Y))
    println(andMap(X, Y))
    println(orMap(X, Y))
  }

  def sentenseGenerateByTemplate(x: Int, y: String, z: Double): String = "%d時の%sは%.1f".format(x, y, z)

  def cryptSentense(message: String): Unit = {
    def cipher(message: String): String = message map {c => c match {
         case c if c.toString.matches("[a-z]") => (219 - c.toByte).toChar
         case _ => c
      }
    }
    val ciphered = cipher(message)
    println(ciphered)
    println(cipher(ciphered))
  }

  def typoglycemia(str: String): String = str.split("\\s+") map {
    case word if word.size > 4 => word.head.toString + word.tail.init.reverse + word.last.toString
    case word => word
  } mkString " "
}
