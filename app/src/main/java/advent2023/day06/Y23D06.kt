package advent2023.day06

import com.twelfthnightdj.advent2021.AocDays

class Y23D06 : AocDays() {
    override var dayId = 6
    var time = listOf<Long>()
    var distance = listOf<Long>()
    var timeB = 0L
    var distanceB = 0L
    override fun partA(): String {
        input.forEachIndexed { index, line ->
            when (index) {
                0 -> time = line.split("\\s+".toRegex()).filterIndexed { idx, _ -> idx > 0 }.map { it.toLong() }
                1 ->  distance = line.split("\\s+".toRegex()).filterIndexed { idx, _ -> idx > 0 }.map { it.toLong() }
            }
        }
        var product = 1L
        time.forEachIndexed { index, t ->
            product *= race(distance[index], t)
        }
        return product.toString()
    }

    override fun partB(): String {
        input.forEachIndexed { index, line ->
            when (index) {
                0 -> timeB = line.split("\\s+".toRegex()).filterIndexed { idx, _ -> idx > 0 }.joinToString("").toLong()
                1 ->  distanceB = line.split("\\s+".toRegex()).filterIndexed { idx, _ -> idx > 0 }.joinToString("").toLong()
            }
        }
        return race(distanceB, timeB).toString()
    }
    private fun race(dist: Long, tim: Long): Long {
        repeat(tim.toInt()) { hold ->
            if (hold * (tim - hold) > dist) {
                return (tim + 1) - (2 * hold)
            }
        }

        return 1L
    }
}