package edu.akkaextention

import java.util.concurrent.atomic.AtomicLong
import akka.actor.Extension

class CountExtensionImpl extends Extension {
  private val counter = new AtomicLong(0)

  def increment() = {
    counter.incrementAndGet()
    println(counter)
  }

}
