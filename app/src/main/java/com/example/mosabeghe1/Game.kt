package com.example.mosabeghe1

import java.lang.Math.max

object Game {
    var level = 1
    var score = 0
    var a = 0
    var b = 0
    var maxScore = -20

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
    fun generateAllChoicesRandomly (): MutableList<Int>{
        val correctChoice = generateCorrectChoice()
        val max = max(b, 4)
        val list = (0 until max).toMutableList()
        list.remove(correctChoice)
        list.shuffle()
        val choices = list.slice(0 .. 2).toMutableList()
        choices.add(correctChoice)
        choices.shuffle()
        return choices

    }
    fun reset(){
        level = 1
        score = 0
        a = 0
        b = 0
    }

    fun setMaximumScore(score: Int){
        if (score > maxScore) maxScore = score
    }

}