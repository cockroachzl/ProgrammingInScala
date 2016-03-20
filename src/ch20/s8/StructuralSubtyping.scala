package ch20.s8

/**
  * Created by liang on 2/26/2016.
  */
object StructuralSubtyping {


}

class Food
abstract class Animal {
  type T <: Food
  def eat(food: T)
}

class Grass extends Food

class Pasture {
  var animals : List[Animal { type T = Grass }] = Nil
}
