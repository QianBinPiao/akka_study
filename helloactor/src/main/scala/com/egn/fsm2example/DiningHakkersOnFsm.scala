package com.egn.fsm2example

import akka.actor._
import akka.actor.FSM._


// Akka adaptation of
// http://www.dalnefre.com/wp/2010/08/dining-philosophers-in-humus/

/*
* Some messages for the chopstick
*/
sealed trait ChopstickMessage
object Take extends ChopstickMessage
object Put extends ChopstickMessage
case class Taken(chopstick: ActorRef) extends ChopstickMessage
case class Busy(chopstick: ActorRef) extends ChopstickMessage

/**
 * Some states the chopstick can be in
 */
sealed trait ChopstickState
case object Available extends ChopstickState
case object Taken extends ChopstickState

/**
 * Some state container for the chopstick
 */
case class TakenBy(hakker: ActorRef)

/*
* A chopstick is an actor, it can be taken, and put back
*/

/**
 * Some fsm hakker messages
 */
sealed trait FSMHakkerMessage
object Think extends FSMHakkerMessage

/**
 * Some fsm hakker states
 */
sealed trait FSMHakkerState
case object Waiting extends FSMHakkerState
case object Thinking extends FSMHakkerState
case object Hungry extends FSMHakkerState
case object WaitForOtherChopstick extends FSMHakkerState
case object FirstChopstickDenied extends FSMHakkerState
case object Eating extends FSMHakkerState

/**
 * Some state container to keep track of which chopsticks we have
 */
case class TakenChopsticks(left: Option[ActorRef], right: Option[ActorRef])

/*
* A fsm hakker is an awesome dude or dudette who either thinks about hacking or has to eat ;-)
*/


/*
* Alright, here's our test-harness
*/
object DiningHakkersOnFsm {

  val system = ActorSystem()

  def main(args: Array[String]): Unit = run()

  def run(): Unit = {
    // Create 5 chopsticks
    val chopsticks = for (i <- 1 to 5) yield system.actorOf(Props[Chopstick], "Chopstick" + i)
    // Create 5 awesome fsm hakkers and assign them their left and right chopstick
    val hakkers = for {
      (name, i) <- List("Ghosh", "Boner", "Klang", "Krasser", "Manie").zipWithIndex
    } yield system.actorOf(Props(classOf[FSMHakker], name, chopsticks(i), chopsticks((i + 1) % 5)))

    hakkers.foreach(_ ! Think)
  }
}
