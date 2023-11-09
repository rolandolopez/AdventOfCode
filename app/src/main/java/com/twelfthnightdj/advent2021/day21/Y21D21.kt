package com.twelfthnightdj.advent2021.day21

import com.twelfthnightdj.advent2021.AocDays

class Y21D21 : AocDays() {
    override var dayId = 21

    var players = mutableListOf<DiracPlayer>()
    var currentPlayer = 0
    var currentDieShowing = 0
    var rolls = 0

    override fun partA(): String {
        setup(input)
        play()
        val final = players[currentPlayer].score * rolls
        return "$final"
    }

    private fun setup(ipt: List<String>) {
        players.add(DiracPlayer(0, ipt[0].split(": ")[1].toInt()))
        players.add(DiracPlayer(1, ipt[1].split(": ")[1].toInt()))
    }

    private fun play(target: Int = 1000) {
        while (players.all { it.score < target }) {
            println()
            val spacesToMove = roll(3)
            players[currentPlayer].apply {
                this.move(spacesToMove)
                println(players[currentPlayer])
            }
            currentPlayer = 1 - currentPlayer
        }
    }

    private fun roll(throws: Int): Int {
        var totalMoves = 0
        repeat(throws) {
            currentDieShowing = ((++currentDieShowing) % 100)
            if (currentDieShowing == 0) currentDieShowing = 100
            totalMoves += currentDieShowing
        }
        rolls += throws
        return totalMoves
    }
}
