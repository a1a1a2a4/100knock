package oneHundredKnock.ishida.second

import java.io.File
import java.io.PrintWriter

import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("hightemp.txt").getLines.toList

    println(countLines(lines))

    val spacedLines = tab2space(lines)
    println(spacedLines)

    extractColumns(lines)

    mergeFile()

    println("Input head n:")
    println(headNumbers(readLine.toInt))

    println("Input tail n:")
    println(tailNumbers(readLine.toInt))

    println("Input filename and split n:")
    splitFile(readLine, readLine.toInt)

    println("Input filename:")
    println(uniqueStringSet(readLine))

    println("Input filename:")
    sortLines(readLine) foreach println

    sortByFreqOfString("hightemp.txt") foreach println
  }

  def linesFromFile(filename: String): List[String] = Source.fromFile(filename).getLines.toList

  def countLines(lines: List[String]): Int = lines.size

  def tab2space(lines: List[String]): List[String] = lines map (_.replace("\t", " "))

  def extractColumns(lines: List[String]): Unit = {
    val col1Writer = new PrintWriter(new File("col1.txt"))
    val col2Writer = new PrintWriter(new File("col2.txt"))

    lines foreach { line =>
      val columns = line split "\t"
      col1Writer.write(columns(0) + "\r\n")
      col2Writer.write(columns(1) + "\r\n")
    }
    col1Writer.close
    col2Writer.close
  }

  def mergeFile(): Unit = {
    val col1lines = Source.fromFile("col1.txt").getLines.toList
    val col2lines = Source.fromFile("col2.txt").getLines.toList

    val writer = new PrintWriter(new File("mergedCol1AndCol2.txt"))

    col1lines zip col2lines foreach { case (col1, col2) =>
      writer.write(col1 + "\t" + col2 + "\r\n")
    }
    writer.close
  }

  def headNumbers(n: Int): List[Int] = Source.stdin.getLines.toList map (_.toInt) take n

  def tailNumbers(n: Int): List[Int] = Source.stdin.getLines.toList map (_.toInt) takeRight n

  def splitFile(filename: String, n: Int): Unit = {
    val lines = Source.fromFile(filename).getLines.toList
    val groupedSize = (lines.size / n.toDouble).ceil.toInt
    (lines grouped groupedSize).zipWithIndex foreach { case (group, i) =>
      val writer = new PrintWriter(new File(filename.replace(".txt", "") + "_" + i + ".txt"))
      group foreach (line => writer.write(line + "\r\n"))
      writer.close
    }
  }

  def uniqueStringSet(filename: String): Set[String] = linesFromFile(filename).map(_.split("\t")(0)).toSet

  def sortLines(filename: String): List[String] = linesFromFile(filename).map(_.split("\t")).sortWith((l1, l2) => l1(2) > l2(2)).map(_.mkString("\t"))

  def sortByFreqOfString(filename: String): List[String] = {
    val lines = linesFromFile(filename) map (_.split("\t"))
    val freqs = lines groupBy (_(0)) mapValues (_.size)
    lines sortWith ((l1, l2) => ( (freqs(l1(0)) >= freqs(l2(0))) && (l1(0).hashCode < l2(0).hashCode) )) map (_.mkString("\t"))
  }
}
