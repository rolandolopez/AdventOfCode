package com.twelfthnightdj.advent2021.day04

import com.twelfthnightdj.advent2021.AocDays

class Y21D04 : AocDays() {

    override var dayId = 4

    override fun partA(): String {
        val bingo = Bingo(input)
        val final = bingo.startAGame()
        return final.toString()
    }

    override fun partB(): String {
        val bingo = Bingo(input)
        val final = bingo.startBGame()
        return final.toString()
    }
}
