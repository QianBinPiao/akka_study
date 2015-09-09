package edu.qianbin

import akka.actor.{Actor, Props}
import akka.contrib.pattern.DistributedPubSubExtension
import akka.contrib.pattern.DistributedPubSubMediator.{Publish, Subscribe}

object ChatClient {
  def props(name: String): Props = Props(classOf[ChatClient], name)

  case class Publish(msg: String)
  case class Message(from: String, text: String)
}

class ChatClient(name: String) extends Actor{

  val mediator = DistributedPubSubExtension(context.system).mediator
  val topic = "chatroom"
  mediator ! Subscribe(topic, self)
  println(s"$name joinded chat room")

  def receive = {

    case ChatClient.Publish(msg) => {
      mediator ! Publish(topic, ChatClient.Message(name, msg))
    }

    case ChatClient.Message(from, text) => {
      val direction = if (sender == self) ">>>>" else s"<< $from:"
      println(s"$name $direction $text")
    }

  }
}
