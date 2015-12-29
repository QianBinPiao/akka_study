package com.egn.injectionexample

/**
  * Created by ypiao on 12/9/15.
  */
trait CommonTimeBankHandler extends TimeBankHandler[PlayerData]{
  override def getTime(playerData : PlayerData): Int = {
    println("Hi this is Common")
    playerData.value
  }
}
