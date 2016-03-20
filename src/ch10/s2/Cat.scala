package ch10.s2

class Cat
(val dangerous : Boolean)
{
}

class Tiger(override val dangerous: Boolean,
            private var age: Int
           ) extends Cat(dangerous)
