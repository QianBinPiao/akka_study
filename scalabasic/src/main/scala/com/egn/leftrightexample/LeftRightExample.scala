package com.egn.leftrightexample


object LeftRightExample extends App {
  val  nums = List(1,2,3,4)
  val sum = nums.foldLeft(0) {
    (acc, num) => acc + num
  }
  println(sum)

  val nums2 = List(1,2,3,4)
  val sum2 = nums.foldRight(0) {
    (acc, num) => acc + num
  }

  println(sum2)
}
