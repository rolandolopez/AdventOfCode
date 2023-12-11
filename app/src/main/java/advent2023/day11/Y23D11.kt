package advent2023.day11

import com.twelfthnightdj.advent2021.AocDays
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.math.abs

class Y23D11 : AocDays() {
    override var dayId = 11
    var allGalaxies = mutableListOf<Space>()
    var xExpansion = mutableSetOf<Int>()
    var yExpansion = mutableSetOf<Int>()
    val PARTB_EXPANSION = 1000000 - 1

    override fun setup() {
        repeat(input.size) { yExpansion.add(it)}
        repeat(input[0].length) { xExpansion.add(it)}
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                val isGalaxy = char == '#'
                val space = Space(
                    x,
                    y,
                    isGalaxy,
                    if (isGalaxy) 0 else 1
                )
                if (isGalaxy) {
                    allGalaxies.add(space)
                    xExpansion.remove(x)
                    yExpansion.remove(y)
                }
            }
        }
    }

    override fun partA(): String {
        return measureDistances().toString()
    }

    private fun measureDistances(partB: Boolean = false): Long {
        var distance = 0L
        (0 until allGalaxies.size).forEach { fromIndex ->
            val fromGalaxy = allGalaxies[fromIndex]
            ((fromIndex + 1) until allGalaxies.size).forEach { toIndex ->
                val toGalaxy = allGalaxies[toIndex]
                distance += abs(fromGalaxy.x - toGalaxy.x) + abs(fromGalaxy.y - toGalaxy.y)
                var xDistance = xExpansion.count {
                    it > min(fromGalaxy.x, toGalaxy.x) && it < max(
                        fromGalaxy.x,
                        toGalaxy.x
                    )
                }
                var yDistance = yExpansion.count {
                    it > min(fromGalaxy.y, toGalaxy.y) && it < max(
                        fromGalaxy.y,
                        toGalaxy.y
                    )
                }
                if (partB) {
                    xDistance *= PARTB_EXPANSION
                    yDistance *= PARTB_EXPANSION
                }
                distance += xDistance + yDistance
            }
        }
        return distance
    }

    override fun partB(): String {
        return measureDistances(true).toString()
    }
}