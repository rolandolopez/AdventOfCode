package advent2021.day02

import com.twelfthnightdj.advent2021.AocDays

class Y21D02 : AocDays() {

    override var dayId = 2
    override fun partA(): String {
        var horizontal = 0
        var depth = 0
        input.forEach {
            val (command, count) = it.split(" ")
            when(command) {
                "forward" -> horizontal += count.toInt()
                "down" -> depth += count.toInt()
                "up" -> depth -= count.toInt()
            }
        }
        return (horizontal * depth).toString()
    }

    override fun partB(): String {
        var horizontal = 0
        var depth = 0
        var aim = 0
        input.forEach {
            val (command, count) = it.split(" ")
            when (command) {
                "forward" -> {
                    horizontal += count.toInt()
                    depth += (aim * count.toInt())
                }
                "down" -> aim += count.toInt()
                "up" -> aim -= count.toInt()
            }
        }
        return (horizontal * depth).toString()
    }

    val trial =  listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2"
    )

}
