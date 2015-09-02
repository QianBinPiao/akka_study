package com.egn.killexample

import akka.actor.{Kill, Props, ActorSystem, Actor}

/**
 * Created by ypiao on 8/6/15.
 */
class InnocentActor extends Actor{
  def receive : Receive = {
    case SayHello => {
      println("I am innocent. Don't kill me")
    }
  }
}

case object SayHello

object InnocentActor extends App {
  val system = ActorSystem("KillActorSystem")
  val innocentActor = system.actorOf(Props[InnocentActor], "innocentActor")
  innocentActor ! SayHello
  innocentActor ! Kill
}
