package advent2025

import com.twelfthnightdj.advent2021.AocDays

class Y25D04 : AocDays() {
    override var dayId = 4
    private val grid = mutableMapOf<String, Char>()
    private var xMax = 0
    private var yMax = 0

    override fun setup() {
        xMax = input[0].length
        yMax = input.size
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                grid.putIfAbsent("$x#$y", c)
            }
        }
        super.setup()
    }

    private fun printGrid() {
        for (y in 0 until yMax) {
            var l = ""
            for (x in 0 until xMax) {
                l += (grid["$x#$y"]).toString()
            }
            println(l)
        }
    }

    override fun partA(): String {
        var totalCount = 0
        for (x in 0 until xMax) {
            for (y in 0 until yMax) {
                if (grid["$x#$y"] != '.') {
                    if (totalNeighbors(x, y) < 4) {
                        totalCount++
                    }
                }
            }
        }
        return totalCount.toString()
    }
    private fun totalNeighbors(x: Int, y:Int): Int {
        var total = 0
        for (dx in -1..1) {
            for (dy in -1..1) {
                if (dx != 0 || dy != 0) {
                    if (grid.getOrDefault("${x + dx}#${y + dy}", '.') != '.') {
                        total++
                    }
                }
            }
        }
        if (total <4) {
            grid["$x#$y"] = 'x'
        }
        return total
    }
}