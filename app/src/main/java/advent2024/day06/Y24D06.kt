package advent2024.day06

import com.twelfthnightdj.advent2021.AocDays
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import utils.Direction

class Y24D06 : AocDays() {
    override var dayId = 6
    private val grid = mutableMapOf<String, Char>()
    private val path = mutableSetOf<String>()
    private val pathB = mutableSetOf<Pair<String, Direction>>()
    private var startingX = 0
    private var startingY = 0
    private var maxX = 0
    private var maxY = 0
    private var currentX = -1
    private var currentY = -1
    private var potentialX = -1
    private var potentialY = -1

    private var direction:Direction = Direction.NORTH
    override fun setup() {
        path.clear()
        grid.clear()
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                grid.putIfAbsent("$x#$y", c)
                if (c == '^') {
                    currentX = x
                    currentY = y
                    startingX = x
                    startingY = y
                    grid["$x#$y"] = '.'
                    path.add("$x#$y")
                }
            }
            maxX = line.length - 1
            maxY = y
        }

        super.setup()
    }

    override fun partA(): String {
        while (true) {
            previewMove()
            val key = "$potentialX#$potentialY"
            if (grid[key] == null) {
                return path.size.toString()
            }
            if (grid[key] == '#') {
                turn()
            } else {
                currentX = potentialX
                currentY = potentialY
                path.add(key)
            }
        }

    }

    override fun reset() {
        currentX = startingX
        currentY = startingY
        super.reset()
    }

    override fun partB(): String {
        GlobalScope.launch {
            var total = 0
            (0..maxX).forEach { x ->
                (0..maxY).forEach yLoop@{ y ->
                    val key = "$x#$y"
                    if (grid[key] == '#') {
                        return@yLoop
                    }
                    grid[key] = '#'
                    currentX = startingX
                    currentY = startingY
                    direction = Direction.NORTH
                    if (walkTheMaze()) {
                        total++
                    }
                    grid[key] = '.'
                }
            }
            println("final total: $total")
        }
        return ""
    }

    private fun walkTheMaze():Boolean {
        pathB.clear()
        pathB.add(Pair("$currentX#$currentY", Direction.NORTH))
        while (true) {
            previewMove()
            val key = "$potentialX#$potentialY"
            if (grid[key] == null) {
                return false
            }
            if (grid[key] == '#') {
                turn()
            } else {
                currentX = potentialX
                currentY = potentialY
                if (pathB.contains(Pair("$currentX#$currentY", direction))) {
                    return true
                }
                pathB.add(Pair("$currentX#$currentY", direction))
            }
        }
    }

    private fun previewMove() {
        potentialX = currentX
        potentialY = currentY
        when (direction) {
            Direction.WEST -> potentialX--
            Direction.EAST -> potentialX++
            Direction.NORTH -> potentialY--
            Direction.SOUTH -> potentialY++
        }
    }
    private fun turn() {
        direction = when (direction) {
            Direction.NORTH -> Direction.EAST
            Direction.EAST -> Direction.SOUTH
            Direction.SOUTH -> Direction.WEST
            Direction.WEST -> Direction.NORTH
        }
    }
}