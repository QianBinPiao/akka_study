package com.egn.partialfunctionexample

object PartialFunctionExample extends App {
  val sample = 1 to 10

  val isEven: PartialFunction[Int, String] = {
    case x if x % 2 == 0 => x + " is even"
  }

  val isOdd: PartialFunction[Int, String] = {
    case x if  x % 2 == 1 => x + " is odd"
  }

  val printFunction: PartialFunction[String, Unit] = {
    case x => println(x)
      x.toString
  }

  val evenNumbers = sample collect isEven
  evenNumbers collect printFunction

  val oddNumbers = sample collect isOdd
  oddNumbers collect printFunction
}
