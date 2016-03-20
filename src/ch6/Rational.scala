package ch6

/**
  * Created by liang on 3/4/2016.
  */

class Rational(n: Int, d: Int) {
  require(d != 0)
  def this(n: Int) = this(n, 1)
  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g
  override def toString = numer + "/" + denom
  def +(rhs: Rational) = new Rational(numer * rhs.denom + rhs.numer * denom, denom * rhs.denom)
  def +(i : Int) = new Rational(numer + denom * i, denom)
  def *(rhs: Rational) = new Rational(numer * rhs.numer, denom * rhs.denom)
  def *(i: Int) = new Rational(numer * i, denom)
  def <(rhs: Rational) = numer * rhs.denom < rhs.numer * denom
  def max(rhs: Rational) = if (this < rhs) rhs else this
  private def gcd(a: Int, b: Int) : Int = if(b == 0) a else gcd(b, a % b)
}

object Rational {
  def apply(n:Int,d:Int) = new Rational(n,d)
  def main(args: Array[String])  {
    val x = new Rational(2,4)
    println(x)
    val y = new Rational(2,3)
    println(x + y)
    println(x < y)
    println(y < x)
    println(x max y)
    println(x + x * y)

  }
}