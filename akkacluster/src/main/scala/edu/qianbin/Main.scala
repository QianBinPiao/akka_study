package edu.qianbin

import akka.actor.{Props, ActorSystem}
import akka.cluster.Cluster


object Main {
  def main(args:Array[String]): Unit = {

    val systemName = "ChatApp"
    val actorSystem1 = ActorSystem(systemName)
    val joinAddress = Cluster(actorSystem1).selfAddress
    Cluster(actorSystem1).join(joinAddress)
    actorSystem1.actorOf(Props[MemberListener], "memberListener")
    actorSystem1.actorOf(Props[RandomUser], "Ben")
    actorSystem1.actorOf(Props[RandomUser], "Kathy")

    Thread.sleep(5000)
    val actorSystem2 = ActorSystem(systemName)
    Cluster(actorSystem2).join(joinAddress)
    actorSystem2.actorOf(Props[RandomUser], "Skye")

    Thread.sleep(3000)
    val actorSystem3 = ActorSystem(systemName)
    Cluster(actorSystem3).join(joinAddress)
    actorSystem3.actorOf(Props[RandomUser], "Miguel")
    actorSystem3.actorOf(Props[RandomUser], "Tyler")

  }
}
