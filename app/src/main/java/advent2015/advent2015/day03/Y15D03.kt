package advent2015.advent2015.day03

import com.twelfthnightdj.advent2021.AocDays

class Y15D03 : AocDays() {
    override var dayId = 3
    var x = 0
    var y = 0
    var grid:MutableMap<String, Int> = mutableMapOf()

    override fun partA(): String {
        grid.putIfAbsent("$x#$y", 1)
        inputAsString.forEach { direction ->
            interpret (direction)
            grid.putIfAbsent("$x#$y", 0)
            grid.computeIfPresent("$x#$y") { _, value ->
                value + 1
            }
        }
        return grid.keys.size.toString()
    }

    private fun interpret(direction: Char) {
        when (direction) {
            '<' -> x--
            '>' -> x++
            '^' -> y++
            'v' -> y--
        }
    }

    override fun reset() {
        x = 0
        y = 0
        grid.clear()
    }

    override fun partB(): String {
        grid.putIfAbsent("$x#$y", 2)
        inputAsString.forEachIndexed { index, direction ->
            if (index % 2 == 0) {
                interpret(direction)
                grid.putIfAbsent("$x#$y", 0)
                grid.computeIfPresent("$x#$y") { _, value ->
                    value + 1
                }
            }
        }
        x = 0
        y = 0
        inputAsString.forEachIndexed { index, direction ->
            if (index % 2 == 1) {
                interpret(direction)
                grid.putIfAbsent("$x#$y", 0)
                grid.computeIfPresent("$x#$y") { _, value ->
                    value + 1
                }
            }
        }
        return grid.keys.size.toString()
    }
}