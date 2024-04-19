package advent2015.day08

import com.twelfthnightdj.advent2021.AocDays

class Y15D08 : AocDays() {
    override var dayId = 8

    override fun partA(): String {
        return input.sumOf { line ->
            line.toCharArray().size - (line.replace("\\\\x[0-9a-f]{2}".toRegex(), "3")
                .replace("\\\"", "2").replace("\\\\".toRegex(), "1").toCharArray().size - 2)
        }.toString()
    }
}