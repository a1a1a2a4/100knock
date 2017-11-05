package oneHundredKnock.ishida.fifth

import scala.io.Source

object Main {

  case class Morph(surface: String, base: String, pos: String, pos2: String)

  def loadSentensesRaw(): Array[String] = Source.fromFile("neko.txt.cabocha").mkString.split("\n{2}\n*")

  def getSentenseLineWordArray(): Array[Array[Array[String]]] = loadSentensesRaw.map(_.split("\n").map(_.split("\t")))

  def wordArr2MorphListMapper(wordArr: Array[Array[Array[String]]]): Array[Array[Morph]] = {
    wordArr map { sentense =>
      sentense map {
        case Array(n, surface, base, pos, pos2, _, _, _, _, _) => Morph(surface, base, pos, pos2)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val wordArr = getSentenseLineWordArray()
    val morphArr: Array[Array[Morph]] = wordArr2MorphListMapper(wordArr)

    // print Morph Sequence of sentense of 3
    morphArr(3) foreach println
  }
}
