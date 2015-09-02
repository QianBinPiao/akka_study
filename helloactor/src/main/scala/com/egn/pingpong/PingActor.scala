package com.egn.pingpong

import akka.actor.{Props, Actor}

/**
 * Created by ypiao on 8/5/15.
 */
class PingActor extends Actor{

  var count = 0

  val pong = context.actorOf(Props[PongActor], "pong_actor")

  def incrementAndPrint() : Unit = {
    count += 1
    println("Ping")
  }

  override def preStart() : Unit = {

  }

  def receive : PartialFunction[Any, Unit] = {
    case startMessage : StartMessage => {
      incrementAndPrint()
      pong ! new PingMessage("This is Ping Instance")
    }
    case pongMessage : PongMessage => {
      incrementAndPrint()
      if(count > 99) {
        sender() ! new StopMessage("Stop Pong")
        context.stop(self)
      } else {
        sender() ! new PingMessage("This is Ping Instance")
      }
    }
  }
}
