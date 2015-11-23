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
  val system = ActorSystem("sample") // dirty design on akka
  val system2 = ActorSystem("sample")
  if(system.equals(system2)) {
    println("you have the same ActorSystem.")
  }

  if(system.eq(system2)) {
    println("you have the same ActorSystem.")
  }

  println(system)
  println(system2)

  val depp = system.actorOf(Props[HollywoodActor], "hoolywoodactor")
  println(depp)
  depp ! "Wonka"

  depp ! new MessageReference("messageReference")

  depp ! new Object

  val depp2 = system2.actorOf(Props[HollywoodActor], "hoolywoodactor")
  println(depp2)
  depp2 ! "Hello"
}
