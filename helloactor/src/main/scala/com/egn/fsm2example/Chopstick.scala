package com.egn.fsm2example

import akka.actor.FSM.Event
import akka.actor.{FSM, Actor}
import scala.concurrent.duration._

class Chopstick extends Actor with FSM[ChopstickState, TakenBy] {
  import context._

  // A chopstick begins its existence as available and taken by no one
  startWith(Available, TakenBy(system.deadLetters))

  // When a chopstick is available, it can be taken by a some hakker
  when(Available) {
    case Event(Take, _) =>
      goto(Taken) using TakenBy(sender) replying Taken(self)
  }

  // When a chopstick is taken by a hakker
  // It will refuse to be taken by other hakkers
  // But the owning hakker can put it back
  when(Taken) {
    case Event(Take, currentState) =>
      stay replying Busy(self)
    case Event(Put, TakenBy(hakker)) if sender == hakker =>
      goto(Available) using TakenBy(system.deadLetters)
  }

  // Initialze the chopstick
  initialize()
}
