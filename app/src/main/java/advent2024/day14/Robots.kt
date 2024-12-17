package advent2024.day14

import utils.Quadrant

class Robots (val maxX: Int, val maxY: Int) {
    private val robots = mutableSetOf<Robot>()
    fun add(robot: Robot) {
        robots.add(robot)
    }

    fun move(seconds: Int) {
        robots.forEach { it.move(seconds, maxX, maxY) }
    }

    fun safetyFactor(): Int {
        val factor = mutableMapOf<Quadrant, Int>()
        robots.forEach { robot ->
            val quad = robot.quadrant
            factor.computeIfPresent(quad) {key, value ->
                value + 1
            }
            factor.putIfAbsent(quad, 1)
        }


        return factor.getOrDefault(Quadrant.TL, 1) *
                factor.getOrDefault(Quadrant.TR, 1) *
                factor.getOrDefault(Quadrant.BL, 1) *
                factor.getOrDefault(Quadrant.BR, 1)
    }

    val size: Int
        get() = robots.size

    override fun toString(): String {
        robots.forEach { println(it) }
        return size.toString()
    }
}