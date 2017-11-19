case class Chunk(morphs: List[Morph], dst: Int, srcs: List[Int]) {

  private val nonSymbolPattern = """([^、。　]+)""".r

  def texts: String = morphs.map(_.surface).mkString

  def noSymbolTexts: String = morphs.map(_.surface).collect{ case nonSymbolPattern(surface) => surface }.mkString

}
