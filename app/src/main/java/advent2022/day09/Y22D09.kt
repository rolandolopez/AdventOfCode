package advent2022.day09

import com.twelfthnightdj.advent2021.AocDays

class Y22D09 : AocDays() {
    override var dayId = 9
    var theInput = input
    var directions = mutableListOf<Pair<String, Int>>()
    var snake = Snake(0,0)

    override fun setup() {
        theInput.forEach { line ->
            val (s, i) = line.split(" ")
            directions.add(Pair(s, i.toInt()))
        }
    }

    override fun partA(): String {
        directions.forEach {
            snake.moveHead(it)
        }
        println("snake: ${snake.tailTrack}")
        return snake.tailTrack.size.toString()
    }

    override fun partB(): String {
        return super.partB()
    }

    override fun reset() {
        super.reset()
    }
}