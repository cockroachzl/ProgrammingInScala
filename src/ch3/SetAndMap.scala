package ch3

/**
  * Created by liang on 3/3/2016.
  */
object SetAndMap {
  def main(args : Array[String]) = {
    val romanNumeral = Map(
      1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V"
    )
    romanNumeral.foreach(println);
  }
}
