package ch20.s9

/**
  * Created by liang on 2/26/2016.
  */
object Direction extends Enumeration {
  val North, East, South, West = Value
}

object Enum {
  def main(args: Array[String]): Unit = {
    for (d <- Direction.values) print(d + " ")

  }
}

object Color extends Enumeration {
  val Red = Value
  val Green = Value
  val Blue = Value
}

