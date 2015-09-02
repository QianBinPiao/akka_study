package com.egn.stashexample

import akka.actor.{Props, ActorSystem, Stash, Actor}

/**
 * Created by ypiao on 8/6/15.
 */
class MyStashActor extends Actor with Stash {

  def receive = accepting

  def accepting : Receive = {
    case Do =>
      println("I am working now")
    case wait =>
      context.become(waiting)

  }

  def waiting : Receive = {
    case Do =>
      println("Ah I am stuck.")
      stash()
    case Resume =>
      unstashAll()
      context.become(accepting)
  }
}

case object Do
case object Wait
case object Resume

object MyStashActor extends App {
  val system = ActorSystem("StashTest")
  val myActorRef = system.actorOf(Props[MyStashActor], "myStashActor")
  myActorRef ! Do
  myActorRef ! Wait
  myActorRef ! Do
  myActorRef ! Resume // I don't understand why this will execute accept.do part
  //myActorRef ! Do
}
