package com.egn.lookup

import scala.concurrent.duration._
import akka.actor.{Terminated, ActorRef, ActorIdentity, ReceiveTimeout, Identify, Actor}
import com.egn.akkaremote.{SubtractResult, MathResult, AddResult, MathOp}

/**
 * Created by ypiao on 8/11/15.
 */
class LookupActor(remotePath: String) extends Actor {

  sendIdentifyRequest()  // how can I understand this

  def sendIdentifyRequest(): Unit = {
    context.actorSelection(remotePath) ! Identify(remotePath)
    import context.dispatcher
    context.system.scheduler.scheduleOnce(3.seconds, self, ReceiveTimeout)
  }

  def receive = identifying

  def identifying: Actor.Receive = {
    case ActorIdentity(`remotePath`, Some(actor)) =>
      context.watch(actor)
      context.become(active(actor))
    case ActorIdentity(`remotePath`, None) => println(s"Remote actor not available: $remotePath")
    case ReceiveTimeout              => sendIdentifyRequest()
    case _                           => println("Not ready yet")
  }

  def active(actor: ActorRef): Actor.Receive = {
    case op: MathOp => actor ! op
    case result: MathResult => result match {
      case AddResult(n1, n2, r) =>
        printf("Add result: %d + %d = %d\n", n1, n2, r)
      case SubtractResult(n1, n2, r) =>
        printf("Sub result: %d - %d = %d\n", n1, n2, r)
    }
    case Terminated(`actor`) =>
      println("Calculator terminated")
      sendIdentifyRequest()
      context.become(identifying)
    case ReceiveTimeout =>
    // ignore

  }
}
