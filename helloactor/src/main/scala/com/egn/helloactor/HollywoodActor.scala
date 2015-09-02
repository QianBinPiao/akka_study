package com.egn.helloactor

import akka.actor.Actor

/**
 * Created by ypiao on 8/4/15.
 */
case class MessageReference(message : String)


class HollywoodActor extends Actor{

  def receive : PartialFunction[Any, Unit]= {
    case message : String =>  {
      println(s"playing the role of $message")
    }
    case messageReference : MessageReference => {
      println(messageReference.message)
    }
  }
}
