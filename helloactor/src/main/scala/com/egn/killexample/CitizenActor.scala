package com.egn.killexample

import akka.actor.Actor

/**
 * Created by ypiao on 8/6/15.
 */

/*
the address for this actor is
"/user/gothemSocietyActor/citizenActor"
 */
class CitizenActor extends Actor{

  def receive : Receive = {
    case MustPayMessage => {
      println("OK I am paying now. Don't kill me.")
    }
    case ReplyMessage => {
      println("Hi officer I am fine now. :Message from cityzen.")
    }
  }

  override def preRestart(reason: Throwable, message: Option[Any]) : Unit = {
    println("I am back and for revenge.")
  }

  override def postRestart(reason: Throwable): Unit = {
    println("I am dying, but I will be back.")
  }
}

case object MustPayMessage
