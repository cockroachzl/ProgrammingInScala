package ch8.s1.methods

/**
  * Created by liang on 2/15/2016.
  */
object FindLongLines {
  def main(args: Array[String]) = {
    val width = args(0).toInt
    for (arg <- args.drop(1))
      LongLines.processFile(arg, width)
  }
}
