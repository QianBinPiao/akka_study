package com.egn.akkaremote

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory
import scala.util.Random
import scala.concurrent.duration._

/**
 * Created by ypiao on 8/11/15.
 */
object CreationApplication {
  def main(args: Array[String]): Unit = {
    if (args.isEmpty || args.head == "startRemoteWorkerSystem")
      startRemoteWorkerSystem()
    if (args.isEmpty || args.head == "startRemoteCreationSystem")
      startRemoteCreationSystem()
  }

  def startRemoteWorkerSystem(): Unit = {
    ActorSystem("CalculatorWorkerSystem", ConfigFactory.load("calculator"))
    println("Started CalculatorWorkerSystem")
  }

  def startRemoteCreationSystem(): Unit = {
    val system =
      ActorSystem("CreationSystem", ConfigFactory.load("remotecreation"))
    val actor = system.actorOf(Props[CreationActor],
      name = "creationActor")

    println("Started CreationSystem")
    import system.dispatcher
    system.scheduler.schedule(1.second, 1.second) {
      if (Random.nextInt(100) % 2 == 0)
        actor ! Multiply(Random.nextInt(20), Random.nextInt(20))
      else
        actor ! Divide(Random.nextInt(10000), (Random.nextInt(99) + 1))
    }

  }
}
