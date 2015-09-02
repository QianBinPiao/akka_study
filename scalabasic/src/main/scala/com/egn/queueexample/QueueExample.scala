package com.egn.queueexample

import scala.collection.immutable._;

/**
 * Created by ypiao on 8/24/15.
 */
object QueueExample extends App{

  var dataque : Queue[Int] = Queue.tabulate(4)(_ + 1)

  while(!dataque.isEmpty) {
    println(dataque.front)
    dataque.drop(1)
  }

}
