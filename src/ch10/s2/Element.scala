package ch10.s2

/**
  * Created by liang on 3/2/2016.
  */
abstract class Element {
  def contents: Array[String]
  def height = _height
  def width = if (height == 0) 0 else contents(0).length
  private def _height = contents.length
}

class ArrayElement(conts: Array[String]) extends Element {
  val contents: Array[String] = conts
}

class ArrayElement2(val contents: Array[String]) extends Element

abstract class Element2 {
  def contents: Array[String]
  val height = contents.length
//  val height = List(1,2,3).size
  val width = if (height == 0) 0 else contents(0).length
}
