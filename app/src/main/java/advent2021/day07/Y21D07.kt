package advent2021.day07

import com.twelfthnightdj.advent2021.AocDays
import kotlin.math.abs

class Y21D07 : AocDays() {
    override var dayId = 7

    var points = emptyList<Int>()
    var possibleAnswer = 0
    val eps = .001
    val diffs = listOf(-1, 1)

    override fun partA(): String {
        points = inputAsString.split(",").map { it.toInt() } ?: emptyList()
        possibleAnswer = kotlin.math.floor(points.average()).toInt()
        go {
            distanceA(it)
        }

        return distanceA(possibleAnswer).toString()
    }

    override fun reset() {
        possibleAnswer = 0
    }

    override fun partB(): String {
        points = inputAsString.split(",").map { it.toInt() } ?: emptyList()
        possibleAnswer = kotlin.math.floor(points.average()).toInt()
        go {
            distanceB(it)
        }

        return distanceB(possibleAnswer).toString()
    }

    fun go(dist: ((Int) -> Int)) {
        var distance = dist(possibleAnswer)
        var step = 100
        var done: Boolean

        while(step > eps) {
            done = false
            diffs.forEach diffs@ { diff ->
                val nx = possibleAnswer + (step * diff)
                val t = dist(nx)

                if (t < distance) {
                    distance = t
                    possibleAnswer = nx
                    done = true
                    return@diffs
                }
            }

            if (!done) {
                step /= 2
            }
        }
    }

    private fun distanceA(x: Int): Int {
        var sum = 0
        points.forEach {
            sum += abs(it - x)
        }
        return sum
    }

    private fun distanceB(x: Int): Int {
        var sum = 0
        points.forEach {
            val d = abs(it - x)
            val fuel = (d * (d + 1))/2
            sum += fuel
        }
        return sum
    }
}
