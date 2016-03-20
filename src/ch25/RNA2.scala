package ch25

import scala.collection.mutable.ArrayBuffer
import scala.collection.{IndexedSeqLike, mutable}

final class RNA2 private(
                          val groups: Array[Int],
                          val length: Int
                        ) extends IndexedSeq[Base]
  with IndexedSeqLike[Base, RNA2] {

  import RNA2._ //import N, S, M

  override def newBuilder: mutable.Builder[Base, RNA2] = new ArrayBuffer[Base] mapResult fromSeq

  def apply(idx: Int): Base = {
    if (idx < 0 || length <= idx)
      throw new IndexOutOfBoundsException
    Base.fromInt(groups(idx / N) >> (idx % N * S) & M)

  }
}

object RNA2 {
  private val S = 2 //number of bits necessary to represent group
  private val N = 32 / S //number of groups that fit in an Int
  private val M = (1 << S) - 1 //Bitmask to isolate a group

  def fromSeq(buf: Seq[Base]): RNA2 = {
    val groups = new Array[Int]((buf.length + N - 1)/N)
    for (i <- 0 until buf.length)
      groups(i / N) |= Base.toInt(buf(i)) << (i % N * S)
    new RNA2(groups, buf.length)
  }

  def apply(bases: Base*) = fromSeq(bases)
}