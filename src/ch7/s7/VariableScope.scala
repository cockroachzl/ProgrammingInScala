package ch7.s7

/**
  * Created by liang on 2/27/2016.
  */
object VariableScope {
  def main(args: Array[String]) = {
    val a = 1; //Here The semicolon is required,
    // otherwise it would have compile  error "Application does not take parameters"
    {
      val a = 2
      println(a)
    }
    println(a)
  }
}
