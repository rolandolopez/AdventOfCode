package advent2023.day18

import utils.Direction

data class ColorSquare(
    var x: Int,
    var y: Int,
    val color: String,
    var neighbors: Set<Direction> = mutableSetOf()
) {

    override fun equals(other: Any?): Boolean {
        other as ColorSquare
        return this.x == other.x && this.y == other.y
    }
}