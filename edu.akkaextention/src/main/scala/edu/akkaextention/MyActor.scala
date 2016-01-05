package edu.akkaextention

import akka.actor.Actor


class MyActor extends Actor {
  def receive = {
    case "Count" =>
      CountExtension(context.system).increment()

    case _ =>
  }
}
