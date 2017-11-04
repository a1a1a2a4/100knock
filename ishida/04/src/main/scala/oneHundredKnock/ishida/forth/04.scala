package oneHundredKnock.ishida.forth

import scala.io.Source
import scala.util.{Try, Success, Failure}

import org.chasen.mecab.Tagger;
import org.chasen.mecab.Node;

object Main {

  Try { System.loadLibrary("MeCab") } match {
    case Success(_) => println("Load Success")
    case Failure(e) => println("Load Failure")
  }

  implicit class NodeExtension(node: Node) {

    def hasNext: Boolean = {
      Try { node.getNext } match {
        case Success(_) => true
        case Failure(_) => false
      }
    }

    def toList: List[Node] = {
      def nodeRecursive(nodeLst: List[Node]): List[Node] = {
        if (nodeLst.head.hasNext) nodeRecursive(nodeLst.head.getNext :: nodeLst) else nodeLst
      }
      nodeRecursive(List(node))
    }

  }

  def main(args: Array[String]): Unit = {

    val text = Source.fromFile("neko.txt").mkString
    val tagger = new Tagger("")
    val node = tagger.parseToNode(text)

    val mecab = mecabNodeListMapper(node.toList)
    //extractVerbBase(mecab) foreach println
    //extractSuddenConnection(mecab) foreach println
    // extractNounPhrase(mecab) foreach println
    extractConcatenationOfNouns(mecab) foreach println
  }

  def mecabNodeListMapper(nodeLst: List[Node]): List[Map[String, String]] = {
    nodeLst map { node =>
      val pattern = "([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+)(?:,[^,]+)*".r
      val feature = Try { node.getFeature } match {
        case Success(feature) => feature
        case Failure(_) => "*,*,*,*,*,*,*,*,*"
      }
      val surface = Try { node.getSurface } match {
        case Success(surface) => surface
        case Failure(_) => "*"
      }
      feature match {
        case pattern(pos, pos1, _, _, _, _, base) => Map("surface" -> surface, "base" -> base, "pos" -> pos, "pos1" -> pos1)
      }
    } toList
  }

  def extractVerbBase(mecab: List[Map[String, String]]): List[String] = {
    mecab collect { case morpheme if morpheme("pos") == "動詞" => morpheme("base") } toList
  }

  def extractSuddenConnection(mecab: List[Map[String, String]]): List[String] = {
    mecab collect { case morpheme if morpheme("pos1") == "サ変接続" => morpheme("base") } toList
  }

  def extractNounPhrase(mecab: List[Map[String, String]]): List[String] = {
    mecab.sliding(3) collect {
      case List(a, and, b) if and("surface") == "の" && a("pos") == "名詞" && b("pos") == "名詞" => a("surface") + and("surface") + b("surface")
    } toList
  }

  def extractConcatenationOfNouns(mecab: List[Map[String, String]]): List[String] = {
    def extractRecursive(mecab: List[Map[String, String]], acm: List[String]): List[String] = {
      val head = mecab.headOption match {
        case Some(h) => h
        case None => return acm
      }

      head match {
        case h if h("pos") == "名詞" => {
          val (satisfy, tail) = mecab.span(m => m("pos") == "名詞")
          if (satisfy.size > 1) {
            val result = satisfy.map(s => s("surface")).toList.mkString
            extractRecursive(tail, result :: acm)
          } else {
            extractRecursive(tail, acm)
          }
        }
        case _ => {
          val (dust, tail) = mecab.span(m => m("pos") != "名詞")
          extractRecursive(tail, acm)
        }
      }
    }
    extractRecursive(mecab, Nil)
  }

}
