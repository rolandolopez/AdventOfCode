package advent2018.day03

import com.twelfthnightdj.advent2021.AocDays

class Y18D03 : AocDays() {
    override var dayId = 3

    override fun partA(): String {
        val grid: MutableMap<String, Int> = mutableMapOf()
        input.forEach { str ->
            val (_, _, startPointPlus, area) = str.split(" ")
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
        }

        return grid.count { it.value > 1 }.toString()
    }

    override fun partB(): String {
        val grid: MutableMap<String, MutableSet<Int>> = mutableMapOf()
        val allIds: MutableSet<Int> = mutableSetOf()
        repeat(input.size) { idx ->
            allIds.add(idx + 1)
        }
        input.forEach { str ->
            val (idString, _, startPointPlus, area) = str.split(" ")
            val id = idString.removePrefix("#").toInt()
            val startPoint = startPointPlus.removeSuffix(":")
            val (x, y) = startPoint.split(",").map { it.toInt() }
            val (dx, dy) = area.split("x").map { it.toInt() }
            repeat(dx) { xChange ->
                repeat(dy) { yChange ->
                    val index = "${x + xChange}#${y + yChange}"
                    if (grid.containsKey(index)) {
                        grid.computeIfPresent(index) { _, value ->
                            value.add(id)
                            value.forEach { foundIds ->
                                allIds.remove(foundIds)
                            }
                            value
                        }
                    } else {
                        grid[index] = mutableSetOf(id)
                    }
                }
            }
        }
        return allIds.first().toString()
    }
}