package com.egn.fsmexample

import akka.actor.{ActorRef, Actor}

sealed trait DiningHakkerMessage
final case class Busy(chopstick: ActorRef) extends DiningHakkerMessage
final case class Put(hakker: ActorRef) extends DiningHakkerMessage
final case class Take(hakker: ActorRef) extends DiningHakkerMessage
final case class Taken(chopstick: ActorRef) extends DiningHakkerMessage
object Eat extends DiningHakkerMessage
object Think extends DiningHakkerMessage

class DiningHakkersOnBecome extends Actor{

  def takenBy(hakker: ActorRef): Receive = {
    case Take(otherHakker) => {
      otherHakker ! Busy(self)
    }
    case Put(`hakker`) => {
      become(available)
    }

  }

  def available: Receive = {

  }

  //A Chopstick begins its existence as available
  def receive = available
}

class Hakker(name: String, left: ActorRef, right: ActorRef) extends Actor {

  def thinking: Receive = {

  }

  def hungry: Receive = {

  }

  def waiting_for()

}
