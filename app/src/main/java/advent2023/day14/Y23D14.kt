package advent2023.day14

import com.twelfthnightdj.advent2021.AocDays

class Y23D14 : AocDays() {
    override var dayId = 14

    var grid = mutableMapOf<String, Char>()
    var totalRows = 0
    var sum = 0
    var highestSpots = mutableMapOf<Int, Int>()

    override fun partA(): String {
        totalRows = input.size
        input.first().forEachIndexed { x, c ->
            when (c) {
                '.' -> {highestSpots[x] = 0}
                else -> {
                    highestSpots[x] = 1
                    grid["$x#0"] = c
                    if (c == 'O') {
                        sum += totalRows

                    }
                }
            }
        }
        input.forEachIndexed lower@ { y, line ->
            if (y == 0) return@lower
            line.forEachIndexed { x, c ->
                when(c) {
                    '.' -> {}
                    'O' -> {
                        val ySpot = highestSpots[x]!!
                        grid["$x#${ySpot}"] = c
                        highestSpots.computeIfPresent(x) { _ , value ->
                            value + 1
                        }
                        sum += totalRows - ySpot

                    }
                    '#' -> {
                        grid["$x#$y"] = c
                        highestSpots[x] = y + 1
                    }
                }
            }
        }
        return sum.toString()
    }

    override fun partB(): String {
        return super.partB()
    }
}