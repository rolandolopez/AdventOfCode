package advent2024.day03

import com.twelfthnightdj.advent2021.AocDays

class Y24D03 : AocDays() {
    override var dayId = 3

    override fun partA(): String {
        var total = 0
        val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
        val matches = regex.findAll(inputAsString)
        matches.forEach { match ->
            total += match.groupValues[1].toInt() * match.groupValues[2].toInt()
        }
        return total.toString()
    }

    override fun partB(): String {
        var total = 0
        var enabled = true
        val regex = "(mul\\((\\d{1,3}),(\\d{1,3})\\))|(do\\(\\))|(don\\'t\\(\\))".toRegex()
        val matches = regex.findAll(inputAsString)
        matches.forEach { match ->
            if (match.groupValues[0] == "do()") {
                enabled = true
                return@forEach
            }
            if (match.groupValues[0] == "don't()") {
                enabled = false
                return@forEach
            }
            if (enabled) {
            total += match.groupValues[2].toInt() * match.groupValues[3].toInt()
            }
        }
        return total.toString()
    }
}