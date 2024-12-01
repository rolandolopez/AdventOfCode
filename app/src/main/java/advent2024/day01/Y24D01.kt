package advent2024.day01

import com.twelfthnightdj.advent2021.AocDays
import kotlin.math.abs

class Y24D01 : AocDays() {
    override var dayId = 1
    private var left = mutableListOf<Int>()
    private var right = mutableListOf<Int>()

    override fun partA(): String {
        input.forEach { line ->
            val (first, second) = line.split("\\s+".toRegex())
            left.add(first.toInt())
            right.add(second.toInt())
        }
        left.sort()
        right.sort()
        var total = 0
        left.forEachIndexed { index, leftValue ->
            total += abs(leftValue - right[index])
        }
        return total.toString()
    }

    override fun partB(): String {
        var total = 0
        input.forEach { line ->
            val (first, second) = line.split("\\s+".toRegex())
            left.add(first.toInt())
            right.add(second.toInt())
        }
        val piper = mutableMapOf<Int, Int>()
        left.forEach { leftValue ->
            total += piper.getOrElse(leftValue) {
                val t = (leftValue * right.count { it == leftValue })
                piper[leftValue] = t
                t
            }
        }
        return total.toString()
    }

    override fun reset() {
        left.clear()
        right.clear()
        super.reset()
    }
}