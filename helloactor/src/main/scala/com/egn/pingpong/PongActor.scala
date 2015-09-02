package com.egn.pingpong

import akka.actor.Actor

/**
 * Created by ypiao on 8/5/15.
 */
class PongActor extends Actor{
  def receive : PartialFunction[Any, Unit] = {
    case pingMessage : PingMessage => {
      println(" Pong~")
      sender() ! new PongMessage("This is Pong")
    }
    case stopMessage : StopMessage => {
      println("Stop Pong Actor")
      context.stop(self)
    }
  }
}
