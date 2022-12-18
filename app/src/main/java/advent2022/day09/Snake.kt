package advent2022.day09

import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.min

class Snake(var x: Int, var y: Int) {
    var head = Point(x, y)
    var tail = Point(x, y)
    var tailTrack = mutableSetOf(tail)

    fun moveToPoint(newPoint: Point) {
        head.x = newPoint.x
        head.y = newPoint.y
        moveTail()
    }

    fun moveHead(instruction: Pair<String, Int>) {
        when (instruction.first) {
            "U" -> moveUp(instruction.second)
            "D" -> moveDown(instruction.second)
            "R" -> moveRight(instruction.second)
            "L" -> moveLeft(instruction.second)
        }
    }

    fun moveUp(distance: Int) {
        repeat(distance) {
            head.y++
            moveTail()
        }
    }

    fun moveDown(distance: Int) {
        repeat(distance) {
            head.y--
            moveTail()
        }
    }

    fun moveRight(distance: Int) {
        repeat(distance) {
            head.x++
            moveTail()
        }
    }

    fun moveLeft(distance: Int) {
        repeat(distance) {
            head.x--
            moveTail()
        }
    }

    private fun moveTail() {
        // horizontal
        when {
            head.x - tail.x >= 2 -> {
                val newY = if (abs(tail.y - head.y) >= 2) {
                    min(tail.y, head.y) + 1
                } else {
                    head.y
                }
                tail = Point(head.x - 1, newY)
                tailTrack.add(tail)
            }
            tail.x - head.x >= 2 -> {
                val newY = if (abs(tail.y - head.y) >= 2) {
                    min(tail.y, head.y) + 1
                } else {
                    head.y
                }
                tail = Point(head.x + 1, newY)
                tailTrack.add(tail)
            }
            // vertical
            head.y - tail.y >= 2 -> {
                val newX = if (abs(tail.x - head.x) >= 2) {
                    min(tail.x, head.x) + 1
                } else {
                    head.x
                }
                tail = Point(newX, head.y - 1)
                tailTrack.add(tail)
            }
            tail.y - head.y >= 2 -> {
                val newX = if (abs(tail.x - head.x) >= 2) {
                    min(tail.x, head.x) + 1
                } else {
                    head.x
                }
                tail = Point(newX, head.y + 1)
                tailTrack.add(tail)
            }
        }
    }
}

class Point(var x: Int, var y: Int) {
    override fun equals(other: Any?): Boolean {
        other as Point
        return x == other.x && y == other.y
    }

    override fun toString(): String {
        return "point ($x, $y)"
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    //Manhattan distance
    fun distanceTo(other: Point) = (x - other.x).absoluteValue + (y - other.y).absoluteValue

    fun cardinalPoints(): Set<Point> =
        setOf(
            Point(x - 1, y),
            Point(x + 1, y),
            Point(x , y - 1),
            Point(x , y + 1),
        )

}