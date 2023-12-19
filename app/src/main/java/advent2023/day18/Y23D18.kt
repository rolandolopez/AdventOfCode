package advent2023.day18

import com.twelfthnightdj.advent2021.AocDays
import utils.Direction
import java.lang.Integer.max
import java.lang.Integer.min
import java.lang.Integer.parseInt

class Y23D18 : AocDays() {
    override var dayId = 18

    var currentPoint = ColorSquare(0, 0, "(#000000)")
    var lagoon = mutableMapOf<String, ColorSquare>()
    var xMin = 0
    var xMax = 0
    var yMin = 0
    var yMax = 0


    override fun partA(): String {
        lagoon["0#0"] = currentPoint.copy()
        input.forEachIndexed { index, line ->
            val (direction, distance, color) = line.split(" ")
            repeat(distance.toInt()) {
                when (direction) {
                    "U" -> {
                        currentPoint.y--
                    }
                    "R" -> {
                        currentPoint.x++
                    }
                    "D" -> {
                        currentPoint.y++
                    }
                    "L" -> {
                        currentPoint.x--
                    }
                }

                xMin = min(xMin, currentPoint.x)
                xMax = max(xMax, currentPoint.x)
                yMin = min(yMin, currentPoint.y)
                yMax = max(yMax, currentPoint.y)
                lagoon.putIfAbsent("${currentPoint.x}#${currentPoint.y}", currentPoint.copy())
            }
        }

        findLoop()

        var insideCount = countTheInside()
        return (lagoon.count() + insideCount).toString()
    }

    private fun countTheInside(): Int {
        var insideCount = 0
        var isInside = false
        var fromNorth = false
        var fromSouth = false

        (yMin..yMax).forEach { y ->
            fromNorth = false
            fromSouth = false
            isInside = false
            (xMin..xMax).forEach { x ->
                val pipe = lagoon["$x#$y"]
                when {
                    pipe == null -> if (isInside) {
                        insideCount++
                    }

                    pipe.neighbors.containsAll(listOf(Direction.NORTH, Direction.SOUTH)) -> {
                        isInside = !isInside
                    }

                    pipe.neighbors.containsAll(listOf(Direction.EAST, Direction.WEST)) -> {}
                    pipe.neighbors.contains(Direction.EAST) -> {
                        if (pipe.neighbors.contains(Direction.NORTH)) {
                            fromNorth = true
                            fromSouth = false
                        } else {
                            fromNorth = false
                            fromSouth = true
                        }
                    }

                    pipe.neighbors.containsAll(listOf(Direction.WEST, Direction.NORTH)) -> {
                        if (fromSouth) {
                            isInside = !isInside
                            fromSouth = false
                        }
                        if (fromNorth) {
                            fromNorth = false
                        }
                    }

                    pipe.neighbors.containsAll(listOf(Direction.WEST, Direction.SOUTH)) -> {
                        if (fromNorth) {
                            isInside = !isInside
                            fromNorth = false
                        }
                        if (fromSouth) {
                            fromSouth = false
                        }
                    }
                }
            }
        }
        return insideCount
    }

    override fun partB(): String {
        lagoon["0#0"] = currentPoint.copy()
        input.forEachIndexed { index, line ->
            val color = line.takeLast(7).take(6)
            val distance = parseInt(color.take(5), 16)
            val direction = color.takeLast(1)
            println("distance; $distance, direction: $direction")
            repeat(distance) {
                when (direction) {
                    "3" -> {
                        currentPoint.y--
                    }
                    "0" -> {
                        currentPoint.x++
                    }
                    "1" -> {
                        currentPoint.y++
                    }
                    "2" -> {
                        currentPoint.x--
                    }
                }

                xMin = min(xMin, currentPoint.x)
                xMax = max(xMax, currentPoint.x)
                yMin = min(yMin, currentPoint.y)
                yMax = max(yMax, currentPoint.y)
                lagoon.putIfAbsent("${currentPoint.x}#${currentPoint.y}", currentPoint.copy())
            }
        }
        return super.partB()
    }

    override fun reset() {
        lagoon.clear()
    }

    private fun findLoop() {
        lagoon.values.forEach { square ->
            square.neighbors = getNeighborsOf(square).toMutableSet()
        }
    }

    private fun getNeighborsOf(p: ColorSquare): Set<Direction> {
        val rSet = mutableSetOf<Direction>()
        // North
        lagoon["${p.x}#${(p.y - 1)}"]?.let { rSet.add(Direction.NORTH) }
        // East
        lagoon["${(p.x + 1)}#${p.y}"]?.let { rSet.add(Direction.EAST) }
        // South
        lagoon["${p.x}#${(p.y + 1)}"]?.let { rSet.add(Direction.SOUTH) }
        // West
        lagoon["${(p.x - 1)}#${p.y}"]?.let { rSet.add(Direction.WEST) }
        return rSet
    }
}