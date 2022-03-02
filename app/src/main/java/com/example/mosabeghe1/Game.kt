package com.example.mosabeghe1

import java.lang.Math.max

object Game {
    var level = 1
    var score = 0
    var a = 0
    var maxA = 100
    var b = 0
    var maxB = 10
    var maxScore = -20
    val choiceList = mutableListOf<Int>()

    fun dice() {
        a = (0..maxA).random()
        b = (1..maxB).random()
    }

    fun generateCorrectChoice(): Int {
        return a % b
    }


    fun nextLevel(isCorrect: Boolean){
        level++
        if (isCorrect == true) {
            score += 5
        } else {
            score -= 2
        }
    }
    fun generateAllChoicesRandomly (){
        val correctChoice = generateCorrectChoice()
        val max = max(b, 4)
        val list = (0 until max).toMutableList()
        list.remove(correctChoice)
        list.shuffle()
        val choices = list.slice(0 .. 2).toMutableList()
        choices.add(correctChoice)
        choices.shuffle()
        choiceList.addAll(choices)
    }

    fun reset(){
        level = 1
        score = 0
        a = 0
        b = 0
        maxA = 100
        maxB = 10
    }

    fun setMaximumScore(score: Int){
        if (score > maxScore) maxScore = score
    }
}