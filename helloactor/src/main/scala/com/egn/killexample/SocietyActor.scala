package com.egn.killexample

import akka.actor.{ActorSystem, Props, Actor}

/**
 * Created by ypiao on 8/6/15.
 */
class SocietyActor extends Actor{

  override def preStart(): Unit = {
    //How to Initialize children here
  }

  val gangsterActorRef = context.actorOf(Props[GangsterActor], "gangsterActor")
  val citizenActorRef = context.actorOf(Props[CitizenActor], "citizenActor")


  def receive : Receive = {
    case AskMessage => {
      println("Government officer is working now. Say hello to Gothem Society.")
      gangsterActorRef ! ReplyMessage
      citizenActorRef ! ReplyMessage
    }
    case PayDayMessage => {
      println("Officer ask gang to collect money.")
      gangsterActorRef ! PayDayMessage

    }
    case DoDirtyWorkMessage => {
      println("Officer ask gang to kill the person.")
      gangsterActorRef ! DoDirtyWorkMessage
    }
  }

}

object SocietyActor extends App {
  val system = ActorSystem("GothemCity")
  val societyActorRef = system.actorOf(Props[SocietyActor], "gothemSocietyActor")

  societyActorRef ! AskMessage

  societyActorRef ! PayDayMessage

  societyActorRef ! DoDirtyWorkMessage

  //societyActorRef ! PayDayMessage
}

case object AskMessage
case object ReplyMessage
