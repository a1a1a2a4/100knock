import scala.io.Source

object Main {

  def problem40(parsed: List[List[Chunk]]) = {
    parsed(2).flatMap(_.morphs).foreach(println)
  }

  def problem41(parsed: List[List[Chunk]]) = {
    parsed(8).foreach{chunk => println(chunk.dst + " " + chunk.texts)}
  }

  def main(args: Array[String]): Unit = {

    val input = Source.fromFile("neko.txt.cabocha").mkString

    val parsed: List[List[Chunk]] = CabochaParser(input) match {
      case Right(some) => some
      case Left(_) => List(Nil)
    }

    //problem40(parsed)
    problem41(parsed)
  }
}
