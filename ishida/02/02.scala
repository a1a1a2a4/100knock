package oneHundredKnock.ishida.second

import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("hightemp.txt").getLines.toList

    println(countLines(lines))

    val spacedLines = tab2space(lines)
    println(spacedLines)
  }

  def countLines(lines: List[String]): Int = lines.size

  def tab2space(lines: List[String]): List[String] = lines map (_.replace("\t", " "))
}
