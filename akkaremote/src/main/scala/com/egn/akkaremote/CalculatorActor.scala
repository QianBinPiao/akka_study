package com.egn.akkaremote

import akka.actor.Actor

/**
 * Created by ypiao on 8/11/15.
 */
class CalculatorActor extends Actor {
  def receive = {
    case Add(n1, n2) =>
      println("Calculating %d + %d".format(n1, n2))
      sender() ! AddResult(n1, n2, n1 + n2)
    case Subtract(n1, n2) =>
      println("Calculating %d - %d".format(n1, n2))
      sender() ! SubtractResult(n1, n2, n1 - n2)
    case Multiply(n1, n2) =>
      println("Calculating %d * %d".format(n1, n2))
      sender() ! MultiplicationResult(n1, n2, n1 * n2)
    case Divide(n1, n2) =>
      println("Calculating %.0f / %d".format(n1, n2))
      sender() ! DivisionResult(n1, n2, n1 / n2)
  }
}
