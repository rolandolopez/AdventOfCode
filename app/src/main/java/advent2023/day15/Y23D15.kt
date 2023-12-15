package advent2023.day15

import com.twelfthnightdj.advent2021.AocDays

class Y23D15 : AocDays() {
    override var dayId = 15
    var sum = 0

    override fun partA(): String {
        inputAsString.split(",").map { sequence ->
            var innerSum = 0
            innerSum = sequence.toList().fold(0) { total, c ->
                (((total + c.code) * 17) % 256)
            }
            sum += innerSum
        }
        return sum.toString()
    }
}