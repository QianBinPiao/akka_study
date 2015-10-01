package com.egn.monadiccollections


object  OptionCollections extends App{

    val words = List("risible", "HELLOWORLD", "gist")

    println(words)

    val lowercase = words find (w=> w ==  w.toLowerCase) // only return Option

    println(lowercase)

    val lowercases = words.filter( w => w == w.toLowerCase) // can return List or Option

    println(lowercases)

    val convertedCases = words.map( _.toLowerCase) // convert the current value

    println(convertedCases)
}
