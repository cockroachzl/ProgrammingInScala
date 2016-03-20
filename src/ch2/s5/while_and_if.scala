package ch2.s5

/**
  * Created by liang on 2/28/2016.
  */
object while_and_if {
  def main(args: Array[String]) = {
    var i = 0
    while(i < args.length) {
      if(i != 0)
        print(" ")
      print(args(i))
      i += 1
    }
    println()
  }
}
