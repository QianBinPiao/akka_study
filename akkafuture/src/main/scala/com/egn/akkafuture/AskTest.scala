package com.egn.akkafuture

import scala.concurrent.{Future, Await}
import akka.actor.{Props, ActorSystem, Actor}
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask

/**
 * Created by ypiao on 8/4/15.
 */

case object AskNameMessage

class TestActor extends Actor {
  def receive = {
    case AskNameMessage => {

      sender() ! "Fred"
    }
    case _ => println("that was unexpected")
  }
}

object AskTest extends App {

  val system = ActorSystem("AskTestSystem")
  val myActor = system.actorOf(Props[TestActor], name="TestActor")

  implicit val timeout = Timeout(5 seconds)
  val future = myActor ? AskNameMessage
  val result = Await.result(future, timeout.duration).asInstanceOf[String]
  println(result)

  val future2 : Future[String] = ask(myActor, AskNameMessage).mapTo[String]
  val result2 = Await.result(future2, 1 second)
  println(result2)

  system.shutdown()
}
