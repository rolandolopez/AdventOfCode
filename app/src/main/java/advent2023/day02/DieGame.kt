package advent2023.day02

import kotlin.math.max

class DieGame (var line: String, val maxRed: Int, val maxGreen: Int, val maxBlue: Int) {
    var gameId = 0
    private lateinit var hands:List<String>
    init {
        gameId = "(\\d+)".toRegex().find(line)?.value?.toInt() ?: 0
        hands = line.split(": ")[1].split("; ")
    }

    fun isGameInvalid(): Boolean {
        hands.forEach { hand ->
            hand.split(", ").forEach {
                val (count, color) = it.split(" ")
                val invalid = when (color) {
                    "red" -> count.toInt() > maxRed
                    "blue" -> count.toInt() > maxBlue
                    "green" -> count.toInt() > maxGreen
                    else -> false
                }
                if (invalid) {
                    return true
                }
            }
        }
        return false
    }

    fun minCube(): Int {
        var mRed = 0
        var mBlue = 0
        var mGreen = 0
        hands.forEach { hand ->
            hand.split(", ").forEach {
                val (count, color) = it.split(" ")
                when (color) {
                    "red" -> mRed = max(count.toInt(), mRed)
                    "blue" -> mBlue = max(count.toInt(), mBlue)
                    "green" -> mGreen = max(count.toInt(), mGreen)
                    else -> {}
                }
            }
        }
        return mRed * mBlue * mGreen
    }
}