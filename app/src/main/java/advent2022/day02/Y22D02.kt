package advent2022.day02

import com.twelfthnightdj.advent2021.AocDays

class Y22D02 :AocDays() {
    override var dayId = 2

    override fun partA(): String {
        val rounds = mutableListOf<Pair<String, String>>()
        input.forEach { round ->
            val hands = round.split(" ")
            rounds.add(Pair(hands[0], hands[1]))
        }
        var total = 0
        rounds.forEach {
            total += fight(it.first, it.second)
        }
        return "$total"
    }

    override fun partB(): String {
        return "part 2"
    }

    private fun fight(opponent: String, mine: String): Int {
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
}