package com.egn.monadiccollections

import scala.util.Try


object TryCollection extends App{
    def loopAndFail(end: Int, failAt: Int): Int = {
        for (i <- 1 to end) {
            println(s"$i ")
            if ( i == failAt) throw new Exception("Too many iterations")
        }
        end
    }

    //loopAndFail(10 ,3)

    val t1 = Try(loopAndFail(3,5))

    println(t1)

    val t2 = Try(loopAndFail(4,3))

    println(t2)

    //java style try/catch using pattern matching in Scala
    try {
        loopAndFail(10 ,3)
    } catch {
        case  ex : Exception => {
            println(ex)
        }
    }

    /*
    try flatMap example if return success then execute flatMap method () or {}
     */

    val returnValue : Try[Int] = Try(loopAndFail(3,5)).flatMap( element => Try(element + 2))

    println(returnValue)

    val returnValue2 : Try[Int] = Try(loopAndFail(4,3)).flatMap{ element => Try(element + 2)}

    println(returnValue2)
}
