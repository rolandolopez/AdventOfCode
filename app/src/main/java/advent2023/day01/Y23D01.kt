package advent2023.day01

import com.twelfthnightdj.advent2021.AocDays

class Y23D01 : AocDays() {
    override var dayId = 1

    var sum = 0
    override fun partA(): String {
//        input.forEach { line ->
//            val first = line.first { it.isDigit() }.toString()
//            val last = line.last { it.isDigit() }.toString()
//            val total = first + last
//            sum += total.toInt()
//        }
        return sum.toString()
    }

    override fun reset() {
        sum = 0
    }

    override fun partB(): String {
        input.forEach { line ->
            val spelledOut = "one|two|three|four|five|six|seven|eight|nine|\\d".toRegex()
            val dellepsOut = "eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|\\d".toRegex()
            val all = spelledOut.findAll(line)
            val lla = dellepsOut.find(line.reversed())
            val first = all.first()
            val last = all.last()
            val tsal = lla?.value?.convert() ?: ""
            val total = if (first.range == last.range) {
                first.value.convert()
            } else {
                first.value.convert() + tsal.convert() // last.value.convert()
            }
            println("line: $line")
            println("total: $total")
            sum += total.toInt()
            println("sum:           $sum")

        }
        return sum.toString()
    }

    private fun String.convert():String {
        return when (this) {
            "one","eno" -> "1"
            "two","owt" -> "2"
            "three","eerht" -> "3"
            "four","ruof" -> "4"
            "five","evif" -> "5"
            "six", "xis" -> "6"
            "seven", "neves" -> "7"
            "eight","thgie" -> "8"
            "nine","enin" -> "9"
            else -> this
        }
    }
}