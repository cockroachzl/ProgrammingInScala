package ch8.s1.methods

import scala.io.Source

/**
  * Created by liang on 2/15/2016.
  */
object LongLines {
  def processFile(filename : String, width: Int): Unit = {
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }

  private def processLine(filename: String, width: Int, line: String): Unit = {
    if (line.length > width)
      println(filename + ": " + line.trim)
  }
}
