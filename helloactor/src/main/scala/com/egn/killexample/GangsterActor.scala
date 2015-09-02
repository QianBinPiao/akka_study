package com.egn.killexample

import akka.actor.{Kill, Identify, Props, Actor}

/**
 * Created by ypiao on 8/6/15.
 */

/*
the address for this actor is
"/user/gothemSocietyActor/gangsterActor"
 */

class GangsterActor extends Actor{

  context.actorSelection("user/gothemSocietyActor/citizenActor")

  def receive : Receive = {
    case PayDayMessage => {
      println("Now it's payday. Give me your money.")

      //actorselect will return ActorSelection type
      context.actorSelection("/user/gothemSocietyActor/citizenActor") ! MustPayMessage

    }
    case  DoDirtyWorkMessage => {
      println("Daum Officer. I have to kill one people...")
      context.actorSelection("/user/gothemSocietyActor/citizenActor") ! Kill
    }
    case ReplyMessage => {
      println("Hi officer I am fine now. :Message from gangster.")
    }
  }
}

case object PayDayMessage
case object DoDirtyWorkMessage
