package advent2023.day04

import com.twelfthnightdj.advent2021.AocDays
import kotlin.math.pow

class Y23D04 : AocDays() {
    override var dayId = 4
    var sumA = 0

    override fun partA(): String {
        input.forEachIndexed { index, line ->
            val bingoLine = line.split(":\\s+".toRegex())[1]
            println("bingoLine $index: $bingoLine")
            val (winners, mine) = bingoLine.split(" | ").map { it.split("\\s+".toRegex()) }
            val c = winners.filter { mine.contains(it) }.size
            when (c) {
                0 -> {}
                1 -> sumA += 1
                else -> sumA += (2.0.pow((c - 1).toDouble()).toInt())
            }
        }
        return sumA.toString()
    }

    override fun partB(): String {
        return super.partB()
    }
}