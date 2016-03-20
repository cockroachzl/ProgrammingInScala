package ch2.s6

/**
  * Created by liang on 2/28/2016.
  */
object foreach_and_for {
  def main(args: Array[String])= {
//    args.foreach(println)

    for (arg <- args) println(arg)
  }
}
