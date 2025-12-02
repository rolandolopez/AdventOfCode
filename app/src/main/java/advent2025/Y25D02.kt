package advent2025

import com.twelfthnightdj.advent2021.AocDays

class Y25D02 : AocDays() {
    override var dayId = 2

    override fun partA(): String {
        val ranges = inputAsString.split(",")
        val regex = "(\\d+)\\1".toRegex()
        var total = 0L
        ranges.forEach { range ->
            val (left, right) = range.split("-").map { it.toLong() }
            for ( id in left..right) {
                val lengthOfId = id.toString().length
                if (lengthOfId % 2 == 0) {
                    val doubled = regex.find(id.toString())?.groupValues?.get(0)?.length == lengthOfId
                    if (doubled) {
                        total += id
                    }
                }
            }
        }
        return total.toString()
    }
}