package advent2022.day01

import com.twelfthnightdj.advent2021.AocDays

class Y22D01 : AocDays() {
    override var dayId = 1

    override fun partA() = process(1)

    override fun partB() = process(3)

    private fun process(limit: Int): String {
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
        return "${elves.sortedDescending().take(limit).sum()}"
    }
}