import scala.util.parsing.combinator._

object CabochaParser extends RegexParsers {

  override def skipWhitespace: Boolean = false

  def cabochaFormatLattice = repsep(sentense, EOS)

  def sentense = chunk.* ^^ { chunks =>
    val idxSrcsMap: Map[Int, List[(Int, Chunk)]] = chunks.groupBy(_._2.dst)
    chunks map { case (idx, chunk) =>
      if (idxSrcsMap.contains(idx)) {
        val newSrcs = idxSrcsMap(idx).map{ _._1 }
        chunk.copy(srcs = newSrcs)
      } else chunk
    }
  }

  def chunk: Parser[(Int, Chunk)] = chunkInfo ~ EOL ~ (morph1 | morph2).* ^^ {
    case idxDst ~ _ ~ morphs => {
      val (idx, dst) = idxDst
      (idx, Chunk(morphs, dst, Nil))
    }
  }

  private val dstPattern = """(.+)(D|O)""".r
  def chunkInfo = "*" ~ space ~ repsep(japanese_text, space) ^^ {
    case _ ~ _ ~ infos => {
      val dst = infos(1) match { case dstPattern(dst, _) => dst.toInt }
      (infos(0).toInt, dst)
    }
  }

  def morph1 = surface ~ space ~ pos ~ comma ~ pos1 ~ comma ~ pos2 ~ comma ~ pos3 ~ comma ~ cjtvf ~ comma ~ usflt ~ comma ~ base ~ comma ~ reading ~ comma ~ pronunce ~ EOL ^^ {
    case (surface ~ _ ~ pos ~ _ ~ pos1 ~ _ ~ _ ~ _ ~ _ ~ _ ~ _ ~ _ ~ _ ~ base ~ _ ~ _ ~ _ ~ _ ~ _ ~ _) => Morph(surface, base, pos, pos1)
  }

  def morph2 = surface ~ space ~ pos ~ comma ~ pos1 ~ comma ~ cjtvf ~ comma ~ usflt ~ comma ~ base ~ comma ~ reading ~ comma ~ pronunce ~ EOL ^^ {
    case (surface ~ _ ~ pos ~ _ ~ pos1 ~ _ ~ _ ~ _ ~ _ ~ _ ~ base ~ _ ~ _ ~ _ ~ _ ~ _) => Morph(surface, base, pos, pos1)
  }

  def surface = """[^ \*,\t\n\r]+""".r

  def pos = japanese_text

  def pos1 = japanese_text

  def pos2 = japanese_text

  def pos3 = japanese_text

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


