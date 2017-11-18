import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {

    val input = Source.fromFile("neko.txt.cabocha").mkString

    val parsed = CabochaParser(input)
    println(parsed)
  }
}
