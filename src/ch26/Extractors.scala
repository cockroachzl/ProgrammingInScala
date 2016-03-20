package ch26

import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by liang on 3/11/2016.
  */
object Email extends ((String,String) => String) {
  def apply(user: String, domain: String) = user + "@" + domain
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }
}

object Twice {
  def apply(s: String) = s + s
  def unapply(ss: String) = {
    val length = ss.length /2
    val half = ss.substring(0, length)
    if (half == ss.substring(length)) Some(half) else None
  }
}

object UpperCase {
  def unapply(s: String) = s.toUpperCase == s
}

object Extractors {
  def userTwiceUpper(s: String) = s match {
    case Email(Twice(x @ UpperCase()), domain) => "match: " + x + " in domain " + domain
    case _ => "no match"
  }

  def main(args: Array[String]) = {
    assert(Email.unapply("liang.zhang@6677.com") equals Some("liang.zhang", "6677.com"))
    assert(Email.unapply("liang.zhang") equals None)
    assert(Email.unapply(Email.apply("liang.zhang", "6677.com")) == Some("liang.zhang", "6677.com"))
    println ("liang@6677.com" match {case Email(user,domain) => user + "@" + domain })


    println(userTwiceUpper("ABAB@6677.com"))
    println(userTwiceUpper("AbaB@6677.com"))
    println(userTwiceUpper("abab@6677.com"))
  }

}


class EmailSpec extends FlatSpec with Matchers {
  "An email extractor " should "extract valid email address" in {
//    Email.unapply("liang.zhang@6677.com") should be Some("liang.zhang", "6677.com")
  }
}