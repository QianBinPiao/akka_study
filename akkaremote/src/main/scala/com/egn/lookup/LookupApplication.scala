package com.egn.lookup

import scala.util.Random
import akka.actor.{Props, ActorSystem}
import com.egn.akkaremote.{Subtract, Add, CalculatorActor}
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._

/**
 * Created by ypiao on 8/11/15.
 */
object LookupApplication {
  def main(args: Array[String]): Unit = {
    if (args.isEmpty || args.head == "startRemoteCalculatorSystem")
      startRemoteCalculatorSystem()
    if (args.isEmpty || args.head == "startRemoteLookupSystem")
      startRemoteLookupSystem()
  }

  def startRemoteCalculatorSystem(): Unit = {
    val system = ActorSystem("CalculatorSystem",
      ConfigFactory.load("calculator"))
    system.actorOf(Props[CalculatorActor], "calculator")

    println("Started CalculatorSystem - waiting for messages")
  }

  def startRemoteLookupSystem(): Unit = {
    val system =
      ActorSystem("LookupSystem", ConfigFactory.load("remotelookup"))
    val remotePath =
      "akka.tcp://CalculatorSystem@127.0.0.1:2552/user/calculator"
    val actor = system.actorOf(Props(classOf[LookupActor], remotePath), "lookupActor")

    println("Started LookupSystem")
    import system.dispatcher
    system.scheduler.schedule(1.second, 1.second) {
      if (Random.nextInt(100) % 2 == 0)
        actor ! Add(Random.nextInt(100), Random.nextInt(100))
      else
        actor ! Subtract(Random.nextInt(100), Random.nextInt(100))
    }
  }
}
