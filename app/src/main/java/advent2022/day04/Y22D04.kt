package advent2022.day04

import com.twelfthnightdj.advent2021.AocDays

class Y22D04 : AocDays() {
    override var dayId = 4
    override fun partA(): String {
        var total = 0
        input.forEach { e ->
            val elves = e.split(",")
            val firstRange = elves[0].split("-").map { it.toInt() }
            val secondRange = elves[1].split("-").map { it.toInt() }
            if (overlapsA(firstRange, secondRange)) {
                total++
            }
        }

        return "$total"
    }

    private fun overlapsA(firstRange: List<Int>, secondRange: List<Int>): Boolean {
        if ((firstRange[0] == secondRange[0]) || (firstRange[1] == secondRange[1])) {
            return true
        }
        return if (firstRange[0] < secondRange[0]) {
            secondRange[1] <= firstRange[1]
        } else {
            firstRange[1] <= secondRange[1]
        }
    }

    private fun overlapsB(firstRange: List<Int>, secondRange: List<Int>): Boolean {
        if ((firstRange[0] == secondRange[0]) || (firstRange[1] == secondRange[1])) {
            return true
        }
        return if (firstRange[0] < secondRange[0]) {
            secondRange[0] <= firstRange[1]
        } else {
            firstRange[0] <= secondRange[1]
        }
    }

    override fun partB(): String {
        var total = 0
        input.forEach { e ->
            val elves = e.split(",")
            val firstRange = elves[0].split("-").map { it.toInt() }
            val secondRange = elves[1].split("-").map { it.toInt() }
            if (overlapsB(firstRange, secondRange)) {
                total++
            }
        }
        return "$total"
    }
}