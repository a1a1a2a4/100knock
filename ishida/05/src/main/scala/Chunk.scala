case class Chunk(morphs: List[Morph], dst: Int, srcs: List[Int]) {
  def texts: String = morphs.map(_.surface).mkString
}
