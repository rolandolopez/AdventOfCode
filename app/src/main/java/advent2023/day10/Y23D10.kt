package advent2023.day10

import com.twelfthnightdj.advent2021.AocDays

class Y23D10 : AocDays() {
    override var dayId = 10
    var pipes = mutableMapOf<String, Pipe>()
    private lateinit var starter: Pipe
    private lateinit var firstPipe: Pipe
    var xMax = 0
    var yMax = 0
    private var loopPipes = mutableMapOf<String, Pipe>()

    override fun setup() {
        yMax = input.size
        xMax = input[0].length
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                when (char) {
                    '.' -> {}
                    else -> {
                        val p = Pipe(x, y, char)
                        pipes.putIfAbsent("$x#$y", p)
                        if (char == 'S') starter = p
                    }
                }
            }
        }
        val nextPipes = mutableListOf<Pipe>()
        // top
        pipes["${starter.x}#${starter.y - 1}"]?.let { up ->
            if (up.south) {
                up.from = "south"
                nextPipes.add(up)
                starter.north = true
            }
        }
        // right
        pipes["${starter.x + 1}#${starter.y}"]?.let { right ->
            if (right.west) {
                right.from = "west"
                nextPipes.add(right)
                starter.east = true
            }
        }
        // bottom
        pipes["${starter.x}#${starter.y + 1}"]?.let { bottom ->
            if (bottom.north) {
                bottom.from = "north"
                nextPipes.add(bottom)
                starter.south = true
            }
        }
        // left
        pipes["${starter.x - 1}#${starter.y}"]?.let { left ->
            if (left.east) {
                left.from = "east"
                nextPipes.add(left)
                starter.west = true
            }
        }
        firstPipe = nextPipes[0]
    }

    override fun partA(): String {


        var firstDirection = firstPipe
        var count = 0
        loopPipes["${firstDirection.x}#${firstDirection.y}"] = firstDirection
        while (firstDirection != starter) {
            count++
            firstDirection.firstDirectionCount = count
            firstDirection = getNextPipe(firstDirection)!!
            loopPipes["${firstDirection.x}#${firstDirection.y}"] = firstDirection
        }

        return ((count + 1) / 2).toString()
    }

    private fun getNextPipe(originalPipe: Pipe): Pipe? {
        return when {
            originalPipe.north && originalPipe.from != "north" -> {
                    val newPipe = pipes["${originalPipe.x}#${originalPipe.y - 1}"]!!
                    newPipe.from = "south"
                    newPipe
            }

            originalPipe.east && originalPipe.from != "east" -> {
                    val newPipe = pipes["${originalPipe.x + 1}#${originalPipe.y}"]!!
                    newPipe.from = "west"
                    newPipe
            }

            originalPipe.south && originalPipe.from != "south" -> {
                    val newPipe = pipes["${originalPipe.x}#${originalPipe.y + 1}"]!!
                    newPipe.from = "north"
                    newPipe
            }

            originalPipe.west && originalPipe.from != "west" -> {
                    val newPipe = pipes["${originalPipe.x - 1}#${originalPipe.y}"]!!
                    newPipe.from = "east"
                    newPipe
            }

            else -> null
        }
    }

    override fun partB(): String {
        var insideCount = 0
        var isInside = false
        var fromNorth = false
        var fromSouth = false
        repeat(yMax) { y ->
            fromNorth = false
            fromSouth = false
            isInside = false
            repeat(xMax) { x ->
                val pipe = loopPipes["$x#$y"]
                when {
                    pipe == null -> if (isInside) insideCount++
                    pipe.north && pipe.south -> isInside = !isInside
                    pipe.west && pipe.east -> {}
                    pipe.east -> {
                        if (pipe.north) {
                            fromNorth = true
                            fromSouth = false
                        } else {
                            fromNorth = false
                            fromSouth = true
                        }
                    }
                    pipe.west && pipe.north -> {
                        if (fromSouth) {
                            isInside = !isInside
                            fromSouth = false
                        }
                        if (fromNorth) {
                            fromNorth = false
                        }
                    }
                    pipe.west && pipe.south -> {
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
        return insideCount.toString()
    }
}