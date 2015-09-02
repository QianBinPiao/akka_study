package com.egn.pingpong

import akka.actor.{Props, ActorSystem}

/**
 * Created by ypiao on 8/5/15.
 */
object PingPongApp extends App{

  val system = ActorSystem("PingPongSystem")

  val ping = system.actorOf(Props[PingActor], "ping_acotr")

  ping ! new StartMessage("Start Ping")

}
