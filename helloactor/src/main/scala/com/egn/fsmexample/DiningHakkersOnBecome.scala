package com.egn.fsmexample

import akka.actor._


sealed trait DiningHakkerMessage
case class Busy(chopstick: ActorRef) extends DiningHakkerMessage
case class Put(hakker: ActorRef) extends DiningHakkerMessage
case class Take(hakker: ActorRef) extends DiningHakkerMessage
case class Taken(chopstick: ActorRef) extends DiningHakkerMessage
object Eat extends DiningHakkerMessage
object Think extends DiningHakkerMessage

object DiningHakkersOnBecome {
  val system = ActorSystem()

  def main(args: Array[String]): Unit = run()

  def run(): Unit = {
    //Create 5 chopsticks
    val chopsticks = for (i <- 1 to 5) yield system.actorOf(Props[Chopstick], "Chopstick" + i)

    //Create 5 awesome hakkers and assign them their left and right chopstick
    val hakkers = for {
      (name, i) <- List("Ghosh", "Boner", "Klang", "Krasser", "Manie").zipWithIndex
    } yield system.actorOf(Props(classOf[Hakker], name, chopsticks(i), chopsticks((i + 1) % 5)))

    //Signal all hakkers that they should start thinking, and watch the show
    hakkers.foreach(_ ! Think)
  }
}



