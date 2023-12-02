package advent2023.day02

import com.twelfthnightdj.advent2021.AocDays

class Y23D02 : AocDays() {
    override var dayId = 2
    var sum = 0

    override fun partA(): String {
        input.forEach { line ->
            var game = DieGame(line, 12, 13, 14)
            if (!game.isGameInvalid()) {
                sum += game.gameId
            }
        }
        return sum.toString()
    }

    override fun reset() {
        sum = 0
    }

    override fun partB(): String {
        input.forEach { line ->
            val game = DieGame(line, 1, 1, 1,)
            sum += game.minCube()
        }
        return sum.toString()
    }
}