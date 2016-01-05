package com.egn.pingpong

import akka.actor.{Props, ActorSystem}

object PingPongApp extends App{

  val system = ActorSystem("PingPongSystem")

  val ping = system.actorOf(Props[PingActor], "ping_actor")

  ping ! new StartMessage("Start Ping")

}
