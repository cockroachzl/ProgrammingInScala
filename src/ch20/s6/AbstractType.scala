package ch20.s6

/**
  * Created by liang on 2/25/2016.
  */
object AbstractType {

  class Food
  abstract class Animal[E <: Food] {
    def eat(food: E)
  }

  class Grass extends Food
  class Cow extends Animal[Grass] {
    override def eat(food: Grass) {}
  }
  def main(args: Array[String])= {

  }
}

object AbstractType2 {
  class Food
  abstract class Animal {
    type T <: Food
    def eat(food: T)
  }

  class Grass extends Food
  class Cow extends Animal {
    type T = Grass
    override def eat(grass: Grass) {}
  }
}
