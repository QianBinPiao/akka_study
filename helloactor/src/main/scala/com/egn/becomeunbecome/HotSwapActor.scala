package com.egn.becomeunbecome

import akka.actor.Actor

/**
 * Created by ypiao on 8/5/15.
 */
class HotSwapActor extends Actor{

  def angry: Receive = {
    case "foo" => { println("foo")}
    case "bar" => context.become(happy)
  }

  def happy: Receive = {
    case "bar" => { println("bar")}
    case "foo" => context.become(angry)
  }

  def receive = {
    case "foo" => {
      println("first need become here.")
      context.become(angry)
    }
    case "bar" => context.become(happy)
  }
}
