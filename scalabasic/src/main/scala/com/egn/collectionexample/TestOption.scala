package com.egn.collectionexample

object TestOption {
  def main(args: Array[String]) {
    val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

    println("capitals.get(\"France\") : " + capitals.get("France"))
    println("capitals.get(\"India\") : " + capitals.get("India"))

    println("capitals.get(\"Japan\") : " + show(capitals.get("Japan")))
    println("capitals.get(\"China\") : " + show(capitals.get("China")))

    //optionFoldTest()

    optionFoldTest(Some(5))

  }

  def show( x : Option[String] ) = x match {
    case Some(s) => s
    case None => "?"
  }

  def optionFoldTest(channel: Option[Int] = None) : Unit = {
    channel.fold {
      println("First fold block.")
    }{
      channelContent =>
        println("Second fold block." + channelContent)

    }
  }

}
