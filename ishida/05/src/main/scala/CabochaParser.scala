import scala.util.parsing.combinator._

case class Chunk(morphs: List[Morph], dst: Int, srcs: List[Int])

case class Morph(surface: String, base: String, pos: String, pos1: String)

object CabochaParser extends RegexParsers {

  override def skipWhitespace: Boolean = false

  def cabochaFormatLattice = repsep(sentense, EOS)

  def sentense = chunk.*

  def chunk = chunkInfo ~ EOL ~ (morph1 | morph2).* ^^ {
    case info ~ _ ~ morphs => {
      println(info)
      Chunk(morphs, 0, Nil)
    }
  }

  def chunkInfo = "*" ~ space ~ repsep(japanese_text, space)

  def morph1 = surface ~ space ~ pos ~ comma ~ pos1 ~ comma ~ cjtvf ~ comma ~ usflt ~ comma ~ base ~ comma ~ reading ~ comma ~ pronunce ~ comma ~ japanese_text ~ comma ~ japanese_text ~ EOL ^^ {
    case (surface ~ _ ~ pos ~ _ ~ pos1 ~ _ ~ _ ~ _ ~ _ ~ _ ~ base ~ _ ~ _ ~ _ ~ _ ~ _ ~ _ ~ _ ~ _ ~ _) => Morph(surface, base, pos, pos1)
  }

  def morph2 = surface ~ space ~ pos ~ comma ~ pos1 ~ comma ~ cjtvf ~ comma ~ usflt ~ comma ~ base ~ comma ~ reading ~ comma ~ pronunce ~ EOL ^^ {
    case (surface ~ _ ~ pos ~ _ ~ pos1 ~ _ ~ _ ~ _ ~ _ ~ _ ~ base ~ _ ~ _ ~ _ ~ _ ~ _) => Morph(surface, base, pos, pos1)
  }

  def surface = """[^ \*,\t\n\r]+""".r

  def pos = japanese_text

  def pos1 = japanese_text

  def cjtvf = japanese_text

  def usflt = japanese_text

  def base = japanese_text

  def reading = japanese_text

  def pronunce = japanese_text

  def japanese_text = """[^ ,\t\n\r]+""".r

  def space = "\t" | " "

  def comma = ","

  def CR = "\r"

  def LF = "\n"

  def EOL = opt(CR) <~ LF

  def EOS = "EOS" <~ EOL

  def apply(input: String): Either[String, List[List[Chunk]]] = parseAll(cabochaFormatLattice, input) match {
    case Success(cabochaData, _) => Right(cabochaData)
    case NoSuccess(errorMessage, next) => Left(s"$errorMessage on line ${next.pos.line} on column ${next.pos.column}")
  }
}


