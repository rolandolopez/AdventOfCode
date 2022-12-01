package advent2022.day01

import com.twelfthnightdj.advent2021.AocDays

class Y22D01 : AocDays() {
    override var dayId = 1

    override fun partA(): String {
        var highestTotal = 0
        var localTotal = 0
        input.forEach { calories ->
            if (calories.isEmpty()) {
                highestTotal = maxOf(highestTotal, localTotal)
                localTotal = 0
            } else {
                localTotal += calories.toInt()
            }

        }
        return "$highestTotal"
    }

    override fun partB(): String {
        return "2022 part B"
    }
}