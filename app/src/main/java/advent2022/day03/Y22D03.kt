package advent2022.day03

import com.twelfthnightdj.advent2021.AocDays

class Y22D03 : AocDays() {
    override var dayId = 3

    override fun partA(): String {
        val holder = mutableListOf<Char>()
        input.forEach { sack ->
            println(sack.length)
            val firstHalf = sack.take(sack.length/2).toCharArray().map { it.toString() }
            val secondHalf = sack.takeLast(sack.length/2)
            run search@{
                firstHalf.forEach {
                    if (secondHalf.contains(it)) {
                        holder.add(it.toCharArray().first())
                        return@search
                    }
                }
            }
        }
        var sum = 0L
        holder.forEach {
           sum += priorityScore(it)
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
        return super.partB()
    }
}