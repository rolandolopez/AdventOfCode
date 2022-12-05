package advent2022.day02

import com.twelfthnightdj.advent2021.AocDays

class Y22D02 :AocDays() {
    override var dayId = 2
    var total = 0
    var rounds = mutableListOf<Pair<String, String>>()

    override fun setup() {
        input.forEach { round ->
            val hands = round.split(" ")
            rounds.add(Pair(hands[0], hands[1]))
        }
    }

    override fun partA(): String {
        rounds.forEach {
            total += fightA(it.first, it.second)
        }
        return "$total"
    }

    override fun reset() {
        total = 0
    }

    override fun partB(): String {
        rounds.forEach {
            total += fightB(it.first, it.second)
        }
        return "$total"
    }

    private fun fightA(opponent: String, mine: String): Int {
        return when (opponent) {
            //rock
            "A" -> when (mine) {
                "X" -> 4 // 3 + 1
                "Y" -> 8 // 6 + 2
                "Z" -> 3 // 0 + 3
                else -> 0
            }
            // paper
            "B" -> when (mine) {
                "X" -> 1 // 0 + 1
                "Y" -> 5 // 3 + 2
                "Z" -> 9 // 6 + 3
                else -> 0
            }
            // scissor
            "C" -> when (mine) {
                "X" -> 7 // 6 + 1
                "Y" -> 2 // 0 + 2
                "Z" -> 6 // 3 + 3
                else -> 0
            }
            else -> 0
        }

    }
    private fun fightB(opponent: String, mine: String): Int {
        return when (opponent) {
            //rock
            "A" -> when (mine) {
                "X" -> 3 // 0 + 3
                "Y" -> 4 // 3 + 1
                "Z" -> 8 // 6 + 2
                else -> 0
            }
            // paper
            "B" -> when (mine) {
                "X" -> 1 // 0 + 1
                "Y" -> 5 // 3 + 2
                "Z" -> 9 // 6 + 3
                else -> 0
            }
            // scissor
            "C" -> when (mine) {
                "X" -> 2 // 0 + 2
                "Y" -> 6 // 3 + 3
                "Z" -> 7 // 6 + 1
                else -> 0
            }
            else -> 0
        }

    }
}