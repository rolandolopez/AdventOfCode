package advent2023.day10

class Pipe (var x: Int, var y: Int, var char: Char) {
    var firstDirectionCount = 0
    var secondDirectionCount = 0
    var north = false
    var south = false
    var west = false
    var east = false
    var from = ""
    init {
        when (char) {
            '|' -> {
                north = true
                south = true
            }
            '-' -> {
                east = true
                west = true
            }
            'L' -> {
                north = true
                east = true
            }
            'J' -> {
                north = true
                west = true
            }
            '7' -> {
                south = true
                west = true
            }
            'F' -> {
                south = true
                east = true
            }
        }
    }

    override fun toString(): String {
        return "$x, $y: $char from: $from, NORTH: $north EAST: $east SOUTH: $south WEST: $west"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pipe
        return (this.x == other.x) && (this.y == other.y) && (this.char == other.char)
    }
}