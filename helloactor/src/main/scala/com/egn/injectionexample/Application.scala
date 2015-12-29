package com.egn.injectionexample

/**
  * Created by ypiao on 12/9/15.
  */
object Application extends App {
  val application = new Application with AsiaTimeBankHandler
  application.doSomething()
  val application2 = new Application2 with CommonTimeBankHandler
  application2.doSomething()
}


class Application extends TimeBankHandler[PlayerData] {
  def doSomething(): Unit ={
    val playerData = PlayerData(10)
    val result = getTime(playerData)
    println(s"result:${result}")
  }
}

class Application2 extends TimeBankHandler[PlayerData] {
  def doSomething(): Unit ={
    val playerData = PlayerData(15)
    val result = getTime(playerData)
    println(s"result:${result}")
  }
}