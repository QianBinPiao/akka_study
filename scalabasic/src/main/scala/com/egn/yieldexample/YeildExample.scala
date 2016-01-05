package com.egn.yieldexample

/**
 * Created by ypiao on 8/12/15.
 */
object YeildExample {

  def main(args: Array[String]) {
    val intArray  = Array(1,2,3,4,5)

    val result = for (e <- intArray) yield {
      e
    }

    println(result.toString)
  }

}
