package com.egn.helloactor

import akka.actor.{ActorSystem, Props}

/**
 * Created by ypiao on 8/4/15.
 */
object CreateActors extends App {
  /*
  Using the ActorSystem will create top-level actors,
  supervised by the actor system’s provided guardian actor,
  while using an actor’s context will create a child actor.
   */
  val system = ActorSystem("sample")

  val depp = system.actorOf(Props[HollywoodActor], "hoolywoodactor")

  depp ! "Wonka"

  depp ! new MessageReference("messageReference")

  depp ! new Object

  system.shutdown()
}
