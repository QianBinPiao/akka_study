package com.egn.companion

/**
 * Created by ypiao on 8/4/15.
 */
class Marker private(val color: String){
  println(s"Creating ${this}")
  override def toString = s"marker color $color"
}

object Marker {
  private val markers = Map (
    "red" -> new Marker("red"),
    "blue" -> new Marker("blue"),
    "yellow" -> new Marker("yellow")
  )

  def getMarker(color: String) = markers.getOrElse(color, new Marker(color))

  def main(args: Array[String]) {
    println(Marker getMarker "blue")
    println(Marker getMarker "blue")
    println(Marker getMarker "red")
    println(Marker getMarker "red")
    println(Marker getMarker "green")
  }
}

/*
The constructor of Marker is declared private; however, the companion object
can access it. Thus, we’re able to create instances of Marker from within the
companion object. If you try to create an instance of Marker outside the class
or the companion object, you’ll get an error.
 */
