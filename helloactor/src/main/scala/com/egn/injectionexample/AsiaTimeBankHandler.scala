package com.egn.injectionexample

/**
  * Created by ypiao on 12/9/15.
  */
trait AsiaTimeBankHandler extends CommonTimeBankHandler{
  override def getTime(playerData : PlayerData): Int = {
    println("Hi this is Asia")
    playerData.value + 10
  }
}
