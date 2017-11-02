package oneHundredKnock.ishida.third

import java.io.FileInputStream
import java.util.zip.GZIPInputStream

import scala.io.Source

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

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

  }

  def loadGZIP2List(filename: String): List[String] = Source.fromInputStream(new GZIPInputStream(new FileInputStream(filename))).getLines.toList

}
