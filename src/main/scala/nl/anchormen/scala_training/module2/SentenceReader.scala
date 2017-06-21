package nl.anchormen.scala_training.module2

import scala.io.Source

object SentenceReader {
  def sentences : List[String] = {
    val inputStream = getClass.getResourceAsStream("rick_morty.txt")
    val source = Source.fromInputStream(inputStream)
    val lineIterator = source.getLines
    lineIterator.toList
  }
}