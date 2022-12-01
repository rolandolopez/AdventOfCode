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
        var highestTotal = 0
        var localTotal = 0
        val elves = mutableListOf<Int>()
        input.forEach { calories ->
            if (calories.isEmpty()) {
                elves.add(localTotal)
                localTotal = 0
            } else {
                localTotal += calories.toInt()
            }
        }
        elves.add(localTotal)
        elves.sortDescending()
        val topThree = elves[0] + elves[1] + elves[2]
        return "$topThree"
    }
}