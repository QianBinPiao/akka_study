package com.egn.loopexample

/**
 * Created by ypiao on 8/19/15.
 */
object ForYieldExample extends App{

  for(i <- 1 to 3 ) {
    println("Ho !")
  }

  val result = for( i <- 1 to 10) yield i * 2

  println(result.toString())

}
