package com.egn.becomeunbecome

import akka.actor.{Props, ActorSystem}

/**
 * Created by ypiao on 8/5/15.
 */
object HotSwapApp extends App{
  val system = ActorSystem("TestHotSwap")
  val refHotSwapActor = system.actorOf(Props[HotSwapActor], name = "hotswap")
  refHotSwapActor ! "foo"
  refHotSwapActor ! "foo"
  refHotSwapActor ! "foo"
  refHotSwapActor ! "foo"

}
