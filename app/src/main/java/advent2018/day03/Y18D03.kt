package advent2018.day03

import com.twelfthnightdj.advent2021.AocDays

class Y18D03 : AocDays() {
    override var dayId = 3
    var grid: MutableMap<String, Int> = mutableMapOf()

    override fun partA(): String {
        input.forEach { str ->
            val (id, _, startPointPlus, area) = str.split(" ")
            val startPoint = startPointPlus.removeSuffix(":")
            val (x, y) = startPoint.split(",").map { it.toInt() }
            val (dx, dy) = area.split("x").map { it.toInt() }
            // horizontal
            repeat(dx) { xChange ->
                repeat(dy) { yChange ->
                    val index = "${x + xChange}#${y + yChange}"
                    grid.putIfAbsent(index, 0)
                    grid.computeIfPresent(index) { _, value ->
                        value + 1
                    }
                }
            }
            println("$str: $id, $startPoint, $area")
        }

        return grid.count { it.value > 1 }.toString()
    }
}