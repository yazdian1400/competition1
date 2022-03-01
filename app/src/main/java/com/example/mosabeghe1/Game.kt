package com.example.mosabeghe1

object Game {
    var level = 1
    var score = 0
    var a = 0
    var b = 0

    fun dice() {
        a = (0..100).random()
        b = (1..10).random()
    }

    fun generateCorrectChoice(): Int {
        return a % b
    }

    fun generateWrongChoices(): MutableList<Int> {
        val choices = mutableListOf(0, 0, 0)
        choices[0] = (0..20).random()
        choices[1] = (0..20).random()
        choices[2] = (0..20).random()
        return choices
        TODO("handle inequality")
    }

    fun nextLevel(isCorrect: Boolean){
        level++
        if (isCorrect == true) {
            score += 5
        } else {
            score -= 2
        }
    }
}