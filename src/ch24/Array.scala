package ch24

import scala.reflect.ClassTag

/**
  * Created by liang on 3/17/2016.
  */
object  ArrayPractice {
//  def evenElems[T:ClassTag](vec: Vector[T]) : Array[T] = {
  def evenElems[T](vec: Vector[T])(implicit m: ClassTag[T]) : Array[T] = {
    val arr = new Array[T]((vec.length+1)/2)
    for (i <- 0 until vec.length by 2)
      arr(i/2) = vec(i)
    arr
  }

  def main(args: Array[String]) = {
    evenElems(Vector(1,2,3,4,5,6))

    evenElems(Vector("this", "is", "a", "test", "run"))
  }
}
