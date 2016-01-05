package edu.akkaextention

import akka.actor.{Props, ActorSystem}


object Application extends App {
  
  val actorSystem = ActorSystem("ExtensionTest")
  val myActor = actorSystem.actorOf(Props[MyActor], "myActor")

  myActor ! "Count"



}
