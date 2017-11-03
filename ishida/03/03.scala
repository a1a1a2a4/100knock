package oneHundredKnock.ishida.third

import java.io.FileInputStream
import java.util.zip.GZIPInputStream

import scala.io.Source

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scalaj.http.Http

object Main {

  case class wikipediaArticle(title: String, text: String)

  lazy val lines = loadGZIP2List("jawiki-country.json.gz")
  val mapper = new ObjectMapper
  mapper.registerModule(DefaultScalaModule)
  lazy val articles: List[wikipediaArticle] = lines map (mapper.readValue(_, classOf[wikipediaArticle])) toList

  def getArticleByTitle(title: String): Option[wikipediaArticle] = articles.find(_.title == title)

  def main(args: Array[String]): Unit = {

    val article = getArticleByTitle("イギリス") match {
      case Some(article) => article
      case None => new wikipediaArticle("Not Found", "This Page is Not Found.")
    }

    val lines = (article.text split "\n").toList

    extractCategoryLines(lines) foreach println

    extractCategoryNames(lines) foreach println

    sectionExtract(lines) foreach println

    extractMediaFiles(lines) foreach println

    val template = extractTemplate(article.text)

    println(getFlagImageURL(template))
  }

  def loadGZIP2List(filename: String): List[String] = Source.fromInputStream(new GZIPInputStream(new FileInputStream(filename))).getLines.toList

  def extractCategoryLines(lines: List[String]): Traversable[String] = {
    val pattern = "(.*\\[\\[Category:.+\\]\\]*.)".r.unanchored
    lines collect { case pattern(line) => line }
  }

  def extractCategoryNames(lines: List[String]): Traversable[String] = {
    val pattern = "\\[\\[Category:(.+)\\]\\]".r.unanchored
    lines collect { case pattern(category) => category }
  }

  def sectionExtract(lines: List[String]): Map[String, Int] = {
    val section = "=(=+)\\s*([^=]+)\\s*=(=+)".r.unanchored
    lines collect { case section(l, name, r) if l.size == r.size => (name, l.size) } toMap
  }

  def extractMediaFiles(lines: List[String]): Traversable[String] = {
    val mediaFile = "\\[\\[ファイル:([^|]+)(|.+)*\\]\\]".r.unanchored
    lines collect { case mediaFile(filename, _) => filename }
  }

  def extractTemplate(text: String): Map[String, String] = {
    val nest = "\\{\\{(.+\n+)+\\}\\}".r
    val info = nest.findFirstIn(text) match {
      case Some(info) => info
      case None => return Map("" -> "")
    }
    val splited = info.split("\n\\|")
    val template = splited.tail.init ++ List(splited.last.split("\n")(0))

    template map (_.split("\\s*=\\s*")) map (arr => (arr(0), arr(1))) toMap
  }

  implicit class MarkupRemovedMap(val template: Map[String, String]) {

    val emphasis = "('{2})|('{3})|('{5})"
    val innerLink = "(\\[\\[)|(\\]\\])"
    val bullet = "\\*"
    val stub = "(\\{\\{)|(\\}\\})"

    def removeEmphasis: Map[String, String] = template.mapValues(line => line.split(emphasis).mkString)

    def removeInnerLink: Map[String, String] = template.mapValues(line => line.split(innerLink).mkString)

    def removeBullet: Map[String, String] = template.mapValues(line => line.split(bullet).mkString)

    def removeStub: Map[String, String] = template.mapValues(line => line.split(stub).map(_.split("\\|")(0)).mkString)

  }

  def getFlagImageURL(template: Map[String, String]): String = {
    val imageUrl = "\"url\":\"([^\"]+)\"".r.unanchored
    val url = "https://commons.wikimedia.org/w/api.php"
    val response = Http(url).param("action", "query")
            .param("titles", "File:" + template("国旗画像"))
            .param("prop", "imageinfo")
            .param("iiprop", "url")
            .param("format", "json").asString.body
    response match { case imageUrl(url) => url }
  }
}
