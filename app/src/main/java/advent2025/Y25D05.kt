package advent2025

import com.twelfthnightdj.advent2021.AocDays

class Y25D05 : AocDays() {
    override var dayId = 5
    var ranges = mutableSetOf<LongRange>()
    var total = 0

    override fun partA(): String {
        var inRanges = true
        input.forEach { str ->
            if (str.isEmpty()) {
                inRanges = false
            } else if (inRanges) {
                val (start, end) = str.split("-").map { it.toLong() }
                ranges.add(LongRange(start, end))
            } else {
                val range = ranges.firstOrNull { it.contains(str.toLong()) }
                if (range != null) total++
            }
        }
        return total.toString()
    }
}