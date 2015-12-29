package com.egn.injectionexample

/**
  * Created by ypiao on 12/9/15.
  */
trait TimeBankHandler[T] {

  def getTime(playerData : T): Int = {
    0
  }

}
