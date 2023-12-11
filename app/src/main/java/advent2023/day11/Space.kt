package advent2023.day11

data class Space(var x: Int, var y: Int, var isGalaxy: Boolean, var stepValue: Int) {
    override fun toString(): String {
        return "$x, $y"
    }
}