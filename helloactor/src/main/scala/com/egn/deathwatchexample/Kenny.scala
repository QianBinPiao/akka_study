package com.egn.deathwatchexample

import akka.actor.Actor

/**
 * Created by ypiao on 8/7/15.
 */
class Kenny extends Actor{

  def receive = {
    case _ => {
      println("Kenny received a message")
      //sender() ! "Reply"
    }
  }

}
