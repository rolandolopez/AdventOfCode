package advent2024.day04

import com.twelfthnightdj.advent2021.AocDays

class Y24D04 : AocDays() {
    override var dayId = 4
    private val grid = mutableMapOf<String, Char>()
    private var maxX = 0
    private var maxY = 0
    override fun setup() {
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                grid.putIfAbsent("$x#$y", c)
            }
            maxX = line.length - 1
            maxY = y
        }

        super.setup()
    }

    override fun partA(): String {
        var total = 0
        val allX = grid.filter { it.value == 'X'}
        allX.forEach { oneX ->
            val (x, y) = oneX.key.split("#").map { it.toInt() }
            (-1..1).forEach { dx ->
                (-1..1).forEach { dy ->
                    if (grid["${x + dx}#${y + dy}"] == 'M') {
                        if (grid["${x + (2*dx)}#${y + (2*dy)}"] == 'A') {
                            if (grid["${x + (3*dx)}#${y + (3*dy)}"] == 'S') {
                                total++
                            }
                        }
                    }
                }
            }
        }
        return total.toString()
    }

    override fun partB(): String {
        var total = 0
        val allA = grid.filter { it.value == 'A' }
        allA.forEach { oneA ->
            val (aX, aY) = oneA.key.split("#").map { it.toInt() }
            var ms = mutableSetOf('M','S')
            ms.remove(grid["${aX - 1}#${aY - 1}"])
            ms.remove(grid["${aX + 1}#${aY + 1}"])
            if (ms.size > 0) {
                return@forEach
            }
            ms = mutableSetOf('M','S')
            ms.remove(grid["${aX - 1}#${aY + 1}"])
            ms.remove(grid["${aX + 1}#${aY - 1}"])
            if (ms.size == 0) {
                total++
            }

        }
        return total.toString()
    }
}