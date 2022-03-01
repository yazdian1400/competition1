package com.example.mosabeghe1

object Game {
    var level = 1
    var a = 0
    var b = 0

    fun dice() {
        a = (0..100).random()
        b = (0..10).random()
    }

    fun generateCorrectChoice(a: Int, b: Int): Int {
        return a % b
    }

    fun generateWrongChoices(b: Int, correctChoice: Int): MutableList<Int> {
        val choices = mutableListOf(0, 0, 0)
        choices[0] = (0..b).random()
        choices[1] = (0..b).random()
        choices[2] = (0..b).random()
        return choices
        TODO("handle inequality")
    }
}