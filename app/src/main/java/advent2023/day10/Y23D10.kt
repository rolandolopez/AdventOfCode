package advent2023.day10

import com.twelfthnightdj.advent2021.AocDays

class Y23D10 : AocDays() {
    override var dayId = 10
    var pipes = mutableMapOf<String, Pipe>()
    private lateinit var starter: Pipe

    override fun setup() {
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
    }

    override fun partA(): String {
        val nextPipes = mutableListOf<Pipe>()
        // top
        pipes["${starter.x}#${starter.y - 1}"]?.let { up ->
            if (up.south) {
                up.from = "south"
                nextPipes.add(up)
            }
        }
        // right
        pipes["${starter.x + 1}#${starter.y}"]?.let { right ->
            if (right.west) {
                right.from = "west"
                nextPipes.add(right)
            }
        }
        // bottom
        pipes["${starter.x}#${starter.y + 1}"]?.let { bottom ->
            if (bottom.north) {
                bottom.from = "north"
                nextPipes.add(bottom)
            }
        }
        // left
        pipes["${starter.x - 1}#${starter.y}"]?.let { left ->
            if (left.east) {
                left.from = "east"
                nextPipes.add(left)
            }
        }

        var firstDirection = nextPipes[0]
        var count = 0

        while (firstDirection != starter) {
            count++
            firstDirection.firstDirectionCount = count

            firstDirection = getNextPipe(firstDirection)!!
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
        return super.partB()
    }
}