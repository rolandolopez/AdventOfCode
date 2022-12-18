package advent2022.day15

import advent2022.day09.Point
import com.twelfthnightdj.advent2021.AocDays

class Y22D15 : AocDays() {
    override var dayId = 15
    lateinit var sensors: Set<Sensor>

    override fun setup() {
        sensors = input.map { line ->
            val location = line.removePrefix("Sensor at x=").substringBefore(":")
            val locationPoint = Point(location.substringBefore(",").toInt(),
            location.substringAfterLast("=").toInt())
            val beacon = line.substringAfterLast("is at x=")
            val beaconPoint = Point(beacon.substringBefore(",").toInt(),
            beacon.substringAfterLast("=").toInt())
            Sensor(locationPoint, beaconPoint)
        }.toSet()
    }

    override fun partA(): String {
        val ranges = sensors.mapNotNull { it.findRange(2000000) }.sortedBy { it.first }
        val red = ranges.drop(1).fold(mutableListOf(ranges.first())) { reduced, range ->
            val lastRange = reduced.last()
            if (range.first <= lastRange.last)
                reduced[reduced.lastIndex] = (lastRange.first..maxOf(lastRange.last, range.last))
            else
                reduced.add(range)
            reduced
        }
        return red.sumOf {
            it.last - it.first
        }.toString()
    }

    override fun partB(): String {
        return super.partB()
    }

    override fun reset() {
        super.reset()
    }
}