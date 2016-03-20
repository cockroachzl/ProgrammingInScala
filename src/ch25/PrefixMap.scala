package ch25

import scala.collection.generic.CanBuildFrom
import scala.collection.mutable

class PrefixMap[T] extends mutable.Map[String, T]
  with mutable.MapLike[String, T, PrefixMap[T]] {

  var suffixes: Map[Char, PrefixMap[T]] = Map.empty
  var value: Option[T] = None

  def withPrefix(prefix: String): PrefixMap[T] =
    if (prefix.isEmpty) this
    else {
      val leading = prefix(0)
      suffixes get leading match {
        case None => suffixes  = suffixes + (leading -> empty)
        case _ =>
      }
      suffixes(leading) withPrefix (prefix substring 1)
    }

  override def update(prefix: String, elem: T) = withPrefix(prefix).value = Some(elem)

  override def remove(prefix: String) : Option[T] =
    if (prefix.isEmpty) {val prev = value; value = None; prev}
    else suffixes get (prefix(0)) flatMap(_.remove(prefix substring 1))

  override def +=(kv: (String, T)): PrefixMap.this.type = { update(kv._1, kv._2); this}

  override def -=(prefix: String): PrefixMap.this.type = {remove(prefix); this}

  override def get(key: String): Option[T] = {
    if (key.isEmpty) value
    else suffixes get(key(0)) flatMap(_.get(key substring 1))
  }

  override def iterator: Iterator[(String, T)] =
    (for (v <- value.iterator) yield ("", v)) ++
      (for ((chr, m) <- suffixes.iterator;
            (s, v) <- m.iterator) yield (chr +: s, v))

  override def empty = PrefixMap.empty[T]
}

object PrefixMap extends {
  def empty[T] = new PrefixMap[T]

  def apply[T](kvs: (String, T)*) : PrefixMap[T] = {
    val m: PrefixMap[T] = empty
    for (kv <- kvs) m += kv
    m
  }

  def newBuilder[T]: mutable.Builder[(String, T), PrefixMap[T]] =
    new mutable.MapBuilder[String, T, PrefixMap[T]](empty)

  implicit def canBuildFrom[T]: CanBuildFrom[PrefixMap[_], (String, T), PrefixMap[T]] =
    new CanBuildFrom[PrefixMap[_], (String, T), PrefixMap[T]] {
      def apply(from: PrefixMap[_]) = newBuilder[T]
      def apply() = newBuilder[T]
    }

}
