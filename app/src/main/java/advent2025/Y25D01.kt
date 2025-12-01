package advent2025

import com.twelfthnightdj.advent2021.AocDays

class Y25D01 : AocDays() {
    override var dayId = 1
    var targetCount = 0
    var highPoint = 100
    var add = highPoint * 10
    var current = 50

    override fun partA(): String {
        input.forEach { str ->
            val direction = str[0]
            val steps = str.substring(1).toInt()
            current = if (direction == 'L') {
                ((current - steps) + add) % highPoint
            } else {
                ((current + steps) + add) % highPoint
            }
            if (current == 0) {
                targetCount++
            }
        }
        return targetCount.toString()
    }
}