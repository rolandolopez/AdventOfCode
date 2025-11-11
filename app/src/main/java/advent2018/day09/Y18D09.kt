package advent2018.day09

import com.twelfthnightdj.advent2021.AocDays

class Y18D09 : AocDays() {
    override var dayId = 9
    var numOfPlayers = 0
    var finalMarble = 0
    var currentPlayerIndex = 1

    override fun partA(): String {
        val parts = inputAsString.split(" ")
        numOfPlayers = parts[0].toInt()
        finalMarble = parts[parts.size - 2].toInt()
        val playerScores: MutableList<Long> = mutableListOf()
        println("numb of players: $numOfPlayers, lastMarble: $finalMarble")
        repeat(numOfPlayers) {
            playerScores.add(0L)
        }
        val circle = Marbles()

        for (index in 3..finalMarble) {
            currentPlayerIndex = (currentPlayerIndex + 1) % numOfPlayers
            if (index % 23L == 0L) {
                var score = playerScores[currentPlayerIndex]
                score += index
                circle.moveCounterClockwise(7)
                score += circle.removeCurrentMarble()
                playerScores[currentPlayerIndex] = score
            } else {
                circle.moveClockwise()
                circle.insertAfterCurrentMarble(index)
            }
        }
        return playerScores.maxOrNull()!!.toString()

    }

    override fun reset() {
        currentPlayerIndex = 1
    }

    override fun partB(): String {
        val parts = inputAsString.split(" ")
        numOfPlayers = parts[0].toInt()
        finalMarble = parts[parts.size - 2].toInt() * 100
        val playerScores: MutableList<Long> = mutableListOf()
        println("numb of players: $numOfPlayers, lastMarble: $finalMarble")
        repeat(numOfPlayers) {
            playerScores.add(0L)
        }
        val circle = Marbles()

        for (index in 3..finalMarble) {
            currentPlayerIndex = (currentPlayerIndex + 1) % numOfPlayers
            if (index % 23L == 0L) {
                var score = playerScores[currentPlayerIndex]
                score += index
                circle.moveCounterClockwise(7)
                score += circle.removeCurrentMarble()
                playerScores[currentPlayerIndex] = score
            } else {
                circle.moveClockwise()
                circle.insertAfterCurrentMarble(index)
            }
        }
        return playerScores.maxOrNull()!!.toString()

    }
}