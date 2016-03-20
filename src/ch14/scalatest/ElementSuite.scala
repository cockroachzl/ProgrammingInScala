package ch14.scalatest

import ch6.Rational
import org.scalatest._

/**
  * Created by liang on 3/5/2016.
  */
class ElementSuite extends Suite{
  def testRational() {
    val r1 = Rational(1,2)
    val r2 = Rational(1,3)
    assert(r2 < r1)
  }
}
