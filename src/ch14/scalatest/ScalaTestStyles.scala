package ch14.scalatest

/**
  *1 FunSuite: xUnit while still giving some of the benefits of BDD
  */
import org.scalatest.FunSuite

class SetFunSuite extends FunSuite {

  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    intercept[NoSuchElementException] {
      Set.empty.head
    }
  }
}

/**
  * 2 FlatSpec: A good first step for teams wishing to move from xUnit to BDD
  */
import org.scalatest.FlatSpec

class SetFlatSpec extends FlatSpec {

  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  it should "produce NoSuchElementException when head is invoked" in {
    intercept[NoSuchElementException] {
      Set.empty.head
    }
  }
}

/**
  * 3 FunSpec: Similar to RSpec: describe
  */

import org.scalatest.FunSpec

class SetFunSpec extends FunSpec {

  describe("A Set") {
    describe("when empty") {
      it("should have size 0") {
        assert(Set.empty.size == 0)
      }

      it("should produce NoSuchElementException when head is invoked") {
        intercept[NoSuchElementException] {
          Set.empty.head
        }
      }
    }
  }
}

/**
  * 4. Similar to Specs/Specs2
*/

import org.scalatest.WordSpec

class SetWordSpec extends WordSpec {

  "A Set" when {
    "empty" should {
      "have size 0" in {
        assert(Set.empty.size == 0)
      }

      "produce NoSuchElementException when head is invoked" in {
        intercept[NoSuchElementException] {
          Set.empty.head
        }
      }
    }
  }
}

/**
  * Because it gives absolute freedom (and no guidance) on how specification text should be written,
  * FreeSpec is a good choice for teams experienced with BDD
  * and able to agree on how to structure the specification text.
*/

import org.scalatest.FreeSpec

class SetFreeSpec extends FreeSpec {

  "A Set" - {
    "when empty" - {
      "should have size 0" in {
        assert(Set.empty.size == 0)
      }

      "should produce NoSuchElementException when head is invoked" in {
        intercept[NoSuchElementException] {
          Set.empty.head
        }
      }
    }
  }
}

/**
  * 6. Spec like xUnit
  */

import org.scalatest.Spec

class SetSpec extends Spec {

  object `A Set` {
    object `when empty` {
      def `should have size 0` {
        assert(Set.empty.size == 0)
      }

      def `should produce NoSuchElementException when head is invoked` {
        intercept[NoSuchElementException] {
          Set.empty.head
        }
      }
    }
  }
}


/**
  * 7. PropSpec
  */
import org.scalatest._
import org.scalatest.prop._

import scala.collection.immutable._

class SetPropSpec extends PropSpec with TableDrivenPropertyChecks with Matchers {

  val examples =
    Table(
      "set",
      BitSet.empty,
      HashSet.empty[Int],
      TreeSet.empty[Int]
    )

  property("an empty Set should have size 0") {
    forAll(examples) { set =>
      set.size should be (0)
    }
  }

  property("invoking head on an empty set should produce NoSuchElementException") {
    forAll(examples) { set =>
      a [NoSuchElementException] should be thrownBy { set.head }
    }
  }
}

/**
  * Trait FeatureSpec is primarily intended for acceptance testing,
  * including facilitating the process of programmers working alongside
  * non-programmers to define the acceptance requirements.
  */

import org.scalatest._

class TVSetFeatureSpec extends FeatureSpec with GivenWhenThen {

  class TVSet {
    private var on: Boolean = false
    def isOn: Boolean = on
    def pressPowerButton() {
      on = !on
    }
  }

  info("As a TV set owner")
  info("I want to be able to turn the TV on and off")
  info("So I can watch TV when I want")
  info("And save energy when I'm not watching TV")

  feature("TV power button") {
    scenario("User presses power button when TV is off") {

      Given("a TV set that is switched off")
      val tv = new TVSet
      assert(!tv.isOn)

      When("the power button is pressed")
      tv.pressPowerButton()

      Then("the TV should switch on")
      assert(tv.isOn)
    }

    scenario("User presses power button when TV is on") {

      Given("a TV set that is switched on")
      val tv = new TVSet
      tv.pressPowerButton()
      assert(tv.isOn)

      When("the power button is pressed")
      tv.pressPowerButton()

      Then("the TV should switch off")
      assert(!tv.isOn)
    }
  }
}