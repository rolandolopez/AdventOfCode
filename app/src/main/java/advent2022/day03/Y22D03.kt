package advent2022.day03

import com.twelfthnightdj.advent2021.AocDays

class Y22D03 : AocDays() {
    override var dayId = 3

    override fun partA(): String {

        var sum = 0L
        input.forEach { sack ->
            val firstHalf = sack.take(sack.length/2).toCharArray()
            val secondHalf = sack.takeLast(sack.length/2).toSet()
            sum += priorityScore(((firstHalf intersect secondHalf).first()))
        }

        return "$sum"
    }

    private fun priorityScore(letter: Char): Int {
        return when {
            letter.isUpperCase() -> letter.code - 38
            else -> letter.code - 96
        }
    }

    override fun partB(): String {
        var sum = 0L
        val workingList = input.toMutableList()
        while(workingList.isNotEmpty()) {
            val trifecta = workingList.take(3).map { it.toCharArray() }
            val intersection = (trifecta[0] intersect trifecta[1].toSet()) intersect trifecta[2].toSet()
            sum += priorityScore((intersection.first()))
            workingList.removeFirst()
            workingList.removeFirst()
            workingList.removeFirst()
        }

        return "$sum"
    }
}