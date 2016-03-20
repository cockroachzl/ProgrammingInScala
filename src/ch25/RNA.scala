package ch25

import scala.collection.generic.CanBuildFrom
import scala.collection.mutable.ArrayBuffer
import scala.collection.{IndexedSeqLike, mutable}

/**
  * Final version, handle take/drop/filter/map/++
  */
final class RNA private(
                          val groups: Array[Int],
                          val length: Int
                        ) extends IndexedSeq[Base]
  with IndexedSeqLike[Base, RNA] {

  import RNA._ //import N, S, M

  override def newBuilder: mutable.Builder[Base, RNA] = RNA.newBuilder

  def apply(idx: Int): Base = {
    if (idx < 0 || length <= idx)
      throw new IndexOutOfBoundsException
    Base.fromInt(groups(idx / N) >> (idx % N * S) & M)

  }
}

object RNA {
  private val S = 2 //number of bits necessary to represent group
  private val N = 32 / S //number of groups that fit in an Int
  private val M = (1 << S) - 1 //Bitmask to isolate a group

  def fromSeq(buf: Seq[Base]): RNA = {
    val groups = new Array[Int]((buf.length + N - 1)/N)
    for (i <- 0 until buf.length)
      groups(i / N) |= Base.toInt(buf(i)) << (i % N * S)
    new RNA(groups, buf.length)
  }

  def apply(bases: Base*) = fromSeq(bases)

  def newBuilder : mutable.Builder[Base, RNA] = new ArrayBuffer[Base] mapResult fromSeq

  implicit def canBuildFrom : CanBuildFrom[RNA,Base, RNA] =
    new CanBuildFrom[RNA, Base, RNA] {
      def apply(): mutable.Builder[Base, RNA] = newBuilder
      def apply(from : RNA): mutable.Builder[Base, RNA] = newBuilder

    }
}