package com.egn.askexample

import akka.actor.{Props, ActorSystem, Actor}
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask


case object AskNameMessage

class AskActor extends Actor {
  def receive = {
    case AskNameMessage =>
      sender().tell("Fred", self)
    case _ => println("that was unexpected")
  }
}

object AskTest extends App{
  val system = ActorSystem("AskTestSystem")
  val myActor = system.actorOf(Props[AskActor], name="myActor")

  implicit val timeout = Timeout(5 seconds)
  val future = myActor ? AskNameMessage
  val result = Await.result(future, timeout.duration).asInstanceOf[String]
  println(result)

  val future2 = myActor.ask(AskNameMessage)
  val result2 = Await.result(future2, timeout.duration)
  println(result2)


}
