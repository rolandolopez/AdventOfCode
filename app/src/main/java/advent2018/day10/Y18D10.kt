package advent2018.day10

import com.twelfthnightdj.advent2021.AocDays

class Y18D10 : AocDays() {
    override var dayId = 10

    override fun partA(): String {
        val gridSerialNumber = inputAsString.toInt()
        val gridDimension = 300
        val grid: MutableMap<String, Long> = mutableMapOf()

        for (x in 0..gridDimension) {
            for (y in 0..gridDimension) {
                grid["$x#$y"] = powerLevel(x, y, gridSerialNumber)
            }
        }

        var bestX = 0
        var bestY = 0
        var currentMax = 0L
        for (x in 0..(gridDimension - 2)) {
            for (y in 0..(gridDimension - 2)) {
                val sum =
                    grid["${x+0}#${y+0}"]!! +
                    grid["${x+1}#${y+0}"]!! +
                    grid["${x+2}#${y+0}"]!! +
                    grid["${x+0}#${y+1}"]!! +
                    grid["${x+1}#${y+1}"]!! +
                    grid["${x+2}#${y+1}"]!! +
                    grid["${x+0}#${y+2}"]!! +
                    grid["${x+1}#${y+2}"]!! +
                    grid["${x+2}#${y+2}"]!!
                if (sum > currentMax) {
                    currentMax = sum
                    bestX = x
                    bestY = y
                }
            }
        }

        return "$bestX,$bestY"
    }

    fun powerLevel(x: Int, y: Int, gsn: Int): Long {
        val rackId = x + 10L
        val level = ((rackId * y) + gsn) * rackId
        return getHundredsDigit(level) - 5

    }

    fun getHundredsDigit(number: Long): Long {
        return (number / 100) % 10
    }
}