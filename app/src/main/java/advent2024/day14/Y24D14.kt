package advent2024.day14

import com.twelfthnightdj.advent2021.AocDays

class Y24D14 : AocDays() {
    override var dayId = 14
    private val robots = Robots(101, 103)

    override fun setup() {
        input.forEach { line ->
            val (p, v) = line.split(" ").map { it.split("=")[1] }
            val (x,y) = p.split(",").map { it.toInt() }
            val (dx, dy) = v.split(",").map { it.toInt() }
            robots.add(Robot(x, y, dx, dy))
        }
        super.setup()
    }

    override fun partA(): String {
        robots.move(100)
        return "${robots.safetyFactor()}"
    }
}