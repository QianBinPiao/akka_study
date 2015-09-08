package com.egn.fsm2example

import akka.actor.FSM.Event
import akka.actor.{FSM, Actor, ActorRef}
import scala.concurrent.duration._
import scala.concurrent.duration.FiniteDuration

class FSMHakker(name: String, left: ActorRef, right: ActorRef) extends Actor with FSM[FSMHakkerState, TakenChopsticks] {

  //All hakkers start waiting
  startWith(Waiting, TakenChopsticks(None, None))

  when(Waiting) {
    case Event(Think, _) =>
      println("%s starts to think".format(name))
      startThinking(5.seconds)
  }

  //When a hakker is thinking it can become hungry
  //and try to pick up its chopsticks and eat
  when(Thinking) {
    case Event(StateTimeout, _) =>
      left ! Take
      right ! Take
      goto(Hungry)
  }

  // When a hakker is hungry it tries to pick up its chopsticks and eat
  // When it picks one up, it goes into wait for the other
  // If the hakkers first attempt at grabbing a chopstick fails,
  // it starts to wait for the response of the other grab
  when(Hungry) {
    case Event(Taken(`left`), _) =>
      goto(WaitForOtherChopstick) using TakenChopsticks(Some(left), None)
    case Event(Taken(`right`), _) =>
      goto(WaitForOtherChopstick) using TakenChopsticks(None, Some(right))
    case Event(Busy(_), _) =>
      goto(FirstChopstickDenied)
  }

  // When a hakker is waiting for the last chopstick it can either obtain it
  // and start eating, or the other chopstick was busy, and the hakker goes
  // back to think about how he should obtain his chopsticks :-)
  when(WaitForOtherChopstick) {
    case Event(Taken(`left`), TakenChopsticks(None, Some(right))) => startEating(left, right)
    case Event(Taken(`right`), TakenChopsticks(Some(left), None)) => startEating(left, right)
    case Event(Busy(chopstick), TakenChopsticks(leftOption, rightOption)) =>
      leftOption.foreach(_ ! Put)
      rightOption.foreach(_ ! Put)
      startThinking(10.milliseconds)
  }

  private def startEating(left: ActorRef, right: ActorRef): State = {
    println("%s has picked up %s and %s and starts to eat".format(name, left.path.name, right.path.name))
    goto(Eating) using TakenChopsticks(Some(left), Some(right)) forMax (5.seconds)
  }

  // When the results of the other grab comes back,
  // he needs to put it back if he got the other one.
  // Then go back and think and try to grab the chopsticks again
  when(FirstChopstickDenied) {
    case Event(Taken(secondChopstick), _) =>
      secondChopstick ! Put
      startThinking(10.milliseconds)
    case Event(Busy(chopstick), _) =>
      startThinking(10.milliseconds)
  }

  // When a hakker is eating, he can decide to start to think,
  // then he puts down his chopsticks and starts to think
  when(Eating) {
    case Event(StateTimeout, _) =>
      println("%s puts down his chopsticks and starts to think".format(name))
      left ! Put
      right ! Put
      startThinking(5.seconds)
  }

  override def preStart() : Unit = {
    initialize()
  }
  // Initialize the hakker
  //initialize()

  private def startThinking(duration: FiniteDuration): State = {
    goto(Thinking) using TakenChopsticks(None, None) forMax duration
  }
}
