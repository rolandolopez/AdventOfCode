package advent2024.day02

import com.twelfthnightdj.advent2021.AocDays
import kotlin.math.abs

class Y24D02 : AocDays() {
    override var dayId = 2

    override fun partA(): String {
        var total = 0
        input.forEach { line ->
            val levels = line.split(" ").map { it.toInt() }
            if (isItSafe(levels)) {
                total++
            }
        }
        return total.toString()
    }

    private fun isItSafe(levels: List<Int>): Boolean {
        if (levels[0] == levels[1]) {
            return false
        }
        val increasing = levels[0] < levels[1]
        if (abs(levels[0] - levels[1]) > 3) return false
        for (i in 1 until levels.size - 1) {
            if (increasing && levels[i] > levels[i + 1]) {
                return false
            }
            if (!increasing && levels[i] < levels[i + 1]) {
                return false
            }
            if (levels[i] == levels[i + 1]) {
                return false
            }
            if (abs(levels[i] - levels[i + 1]) > 3) {
                return false
            }
        }
        return true
    }

    override fun partB(): String {
        var total = 0
        input.forEach { line ->
            val levels = line.split(" ").map { it.toInt() }.toMutableList()
            if (isItSafe(levels)) {
                total++
                return@forEach
            }
            repeat(levels.size) { index ->
                val newLevels = levels.toMutableList()
                newLevels.removeAt(index)
                if (isItSafe(newLevels)) {
                    total++
                    return@forEach
                }
            }
        }
        return total.toString()
    }
}