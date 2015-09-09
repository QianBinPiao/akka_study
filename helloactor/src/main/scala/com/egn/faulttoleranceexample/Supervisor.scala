package com.egn.faulttoleranceexample

import scala.concurrent.duration._
import akka.actor.SupervisorStrategy._
import akka.actor.{OneForOneStrategy, Props, Actor}

class Supervisor extends Actor{

  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
    case _: ArithmeticException      => Resume
    case _: NullPointerException     => Restart
    case _: IllegalArgumentException => Stop
    case _: Exception                => Escalate
  }

  def receive = {
    case  p: Props => sender() ! context.actorOf(p)
  }


}
