package com.egn.akkaconfig

import akka.actor.ActorSystem

/**
  * Created by ypiao on 11/23/15.
  */
object ConfigPath extends App{

  val actorSystem = ActorSystem("ActorRoot")

  println(actorSystem.settings.config)

}
