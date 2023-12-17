package advent2023.day16

import utils.Direction

class LaserSquare (var x: Int, var y: Int, var c: Char) {
    var passedHeading = mutableSetOf<Direction>()
    var isEnergized = false
    fun isSpace() = c == '.'
    fun isVerticalMirror() = c == '|'
    fun isHorizontalMirror() = c == '-'
    fun isDemocraticMirror() = c == '\\'
    fun isRepublicanMirror() = c == '/'

    override fun toString(): String {
        return "($x, $y) = $c"
    }
}