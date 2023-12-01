package advent2023.day01

import com.twelfthnightdj.advent2021.AocDays

class Y23D01 : AocDays() {
    override var dayId = 1

    var sum = 0
    override fun partA(): String {
        input.forEach { line ->
            val first = line.first { it.isDigit() }.toString()
            val last = line.last { it.isDigit() }.toString()
            val total = first + last
            sum += total.toInt()
        }
        return sum.toString()
    }

    override fun partB(): String {
        return super.partB()
    }
}