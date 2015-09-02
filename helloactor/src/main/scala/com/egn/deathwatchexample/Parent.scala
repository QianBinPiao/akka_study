package com.egn.deathwatchexample

import akka.actor.{ActorRef, PoisonPill, ActorSystem, Terminated, Props, Actor}

/**
 * Created by ypiao on 8/7/15.
 */
class Parent extends Actor{
  val kenny = context.actorOf(Props[Kenny], name = "Kenny")

  override def preStart() : Unit = {

    context.watch(kenny)
  }

  def receive = {
    case Terminated(`kenny`) => println("OMG, they killed Kenny")
    case _ => println("Parent received a message")
  }
}

object DeathWatchTest extends App {

  val system = ActorSystem("DeathWatchTest")

  val parent = system.actorOf(Props[Parent], name = "Parent")

  val kenny = system.actorSelection("/user/Parent/Kenny")
  kenny ! "Hello"

  Thread.sleep(3000)

  kenny ! PoisonPill

  Thread.sleep(3000)

  system.shutdown()


}
