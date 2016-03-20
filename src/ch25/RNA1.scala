package ch25

final class RNA1 private(val groups: Array[Int], val length: Int)
  extends IndexedSeq[Base] {

  import RNA1._ //import N, S, M

  def apply(idx: Int): Base = {
    if (idx < 0 || length <= idx)
      throw new IndexOutOfBoundsException
    Base.fromInt(groups(idx/N) >> (idx % N * S) & M)

  }
}

object RNA1 {
  private val S = 2 //number of bits necessary to represent group
  private val N = 32 / S //number of groups that fit in an Int
  private val M = (1 << S) - 1 //Bitmask to isolate a group

  def fromSeq(buf: Seq[Base]): RNA1 = {
    val groups = new Array[Int]((buf.length + N - 1)/N)
    for (i <- 0 until buf.length)
      groups(i / N) |= Base.toInt(buf(i)) << (i % N * S)
    new RNA1(groups, buf.length)
  }

  def apply(bases: Base*) = fromSeq(bases)

  def main(args : Array[String]): Unit = {
    val xs = List(A, G, T, A)
    println(xs)
    println(RNA1.fromSeq(xs))
    println(RNA1(A, U, G, G, T))
  }
}
