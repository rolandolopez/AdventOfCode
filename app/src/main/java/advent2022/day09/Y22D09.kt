package advent2022.day09

import com.twelfthnightdj.advent2021.AocDays

class Y22D09 : AocDays() {
    override var dayId = 9
    var directions = mutableListOf<Pair<String, Int>>()
    var snake = Snake(0,0)

    override fun setup() {
        input.forEach { line ->
            val (s, i) = line.split(" ")
            directions.add(Pair(s, i.toInt()))
        }
    }

    override fun partA(): String {
        directions.forEach {
            snake.moveHead(it)
        }
        return snake.tailTrack.size.toString()
    }

    override fun partB(): String {
        val megaSnake = MegaSnake(0,0,9)
        directions.forEach {
            megaSnake.moveHead(it)
            println("head (${megaSnake.all.first().head}), tail (${megaSnake.all.last().tail}) ($it): ${megaSnake.finalCheck()}")
        }
        megaSnake.printAll()
        return megaSnake.finalCheck()
    }

    override fun reset() {
        super.reset()
    }
}