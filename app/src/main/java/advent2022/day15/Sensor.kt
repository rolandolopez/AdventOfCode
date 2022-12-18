package advent2022.day15

import advent2022.day09.Point
import kotlin.math.absoluteValue

class Sensor (val point: Point, nearestBeacon: Point) {
    val distance: Int = point.distanceTo(nearestBeacon)

    fun findRange(y: Int): IntRange? {
        val scanWidth = distance - (point.y - y).absoluteValue
        return (point.x - scanWidth..point.x + scanWidth).takeIf { it.first <= it.last }
    }
}