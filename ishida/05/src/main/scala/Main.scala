import java.io.{FileOutputStream, OutputStreamWriter}

import oracle.jvm.hotspot.jfr.StreamWriter

import scala.io.Source
import scala.collection.mutable.MutableList

object Main {

  def problem40(parsed: List[List[Chunk]]) = {
    parsed(2).flatMap(_.morphs).foreach(println)
  }

  def problem41(parsed: List[List[Chunk]]) = {
    parsed(8).foreach{chunk => println(chunk.dst + " " + chunk.texts)}
  }

  def problem42(parsed: List[List[Chunk]]) = {
    for (sentense <- parsed: List[List[Chunk]]; chunk <- sentense: List[Chunk]; if chunk.dst != -1)
      println(chunk.noSymbolTexts + "\t" + sentense(chunk.dst).noSymbolTexts)
  }

  def problem43(parsed: List[List[Chunk]]) = for (
    sentense <-parsed;
    chunk <- sentense;
    if chunk.containsNoun && chunk.dst != -1 && sentense(chunk.dst).containsVerb
  ) {
    println(chunk.noSymbolTexts + "\t" + sentense(chunk.dst).noSymbolTexts)
  }

  /**
    * idxs2Chunks sentenseとchunkのidxのリストを受け取りchunkのリストを返す
    * @param sentense
    * @param idxs
    * @return
    */
  def idxs2Chunks(sentense: List[Chunk], idxs: List[Int]): List[Chunk] = idxs.map{ sentense(_) }

  /**
    * inspectChunk Chunkの係り元があればTrue, 無ければfalse
    * @param chunk
    * @return
    */
  def inspectChunk(chunk: Chunk): Boolean = chunk match {
    case chunk if chunk.srcs.size > 0 => true
    case _ => false
  }

  implicit class ChainedChunkList(underlying: List[Chunk]) {
    def chained = underlying.map(_.noSymbolTexts).mkString(" -> ")
  }

  def problem44(parsed: List[List[Chunk]]) = {

    /**
      * パースされたSentense毎のChunkのリストをSentense、Chunk毎のDOT言語のリストに変換
      * @param parsed
      * @return
      */
    def translate2DOT(parsed: List[List[Chunk]]): List[List[String]] = parsed map { sentense =>
      val rootChunks = sentense filter { chunk => chunk.dst != -1 && chunk.srcs.size != 0 }
      val result = new MutableList[String]

      def recursive(chunks: List[Chunk], stack: List[Chunk]): Unit = chunks match {
        case Nil => return
        case chunks => {
          val head = chunks.head
          val tail = chunks.tail
          if (inspectChunk(head)) {
            val dependentSourceChunks = idxs2Chunks(sentense, head.srcs)
            recursive(dependentSourceChunks, head :: stack)
          } else {
            if (stack.size > 1) result += stack.chained
            return
          }
        }
      }
      rootChunks foreach { rootChunk => recursive(List(rootChunk), Nil) }

      result.toList
    } filter { _.size != 0 }

    /**
      * DOT言語を出力する
      * @param document
      */
    def printDOTLanguage(document: List[List[String]]): Unit = document.zipWithIndex foreach { case (sentense, i) =>
      println("digraph sentense" + i + " {")
      println(sentense.mkString("\t", ";\n\t", ";"))
      println("}")
    }

    /**
      * DOT言語をStringに変換する
      * @param document
      * @return
      */
    def DOTLangulage2text(document: List[List[String]]): String = document.zipWithIndex map { case (sentense, i) =>
      "digraph sentense" + i + " {\n" + sentense.mkString("\t", ";\n\t", ";\n") + "}\n"
    } mkString("\n\n")

    /**
      * ファイルにStringを書き込む
      */
    def write2File(fileName: String, str: String): Unit = {
      val fileOutputStream = new FileOutputStream(fileName, true)
      val writer = new OutputStreamWriter(fileOutputStream)

      writer.write(str)
      writer.close()
    }

    val DOTdocument = translate2DOT(parsed)
    //printDOTLanguage(DOTdocument)
    val text = DOTLangulage2text(DOTdocument)
    write2File("neko.txt.dot", text)
  }

  def main(args: Array[String]): Unit = {

    val input = Source.fromFile("neko.txt.cabocha").mkString

    val parsed: List[List[Chunk]] = CabochaParser(input) match {
      case Right(some) => some
      case Left(_) => List(Nil)
    }

//    problem40(parsed)
//    problem41(parsed)
//    problem42(parsed)
//    problem43(parsed)
    problem44(parsed)
  }
}
