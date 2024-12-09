package advent2024.day08

import com.twelfthnightdj.advent2021.AocDays
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Y24D08 : AocDays() {
    override var dayId = 8
    private val grid = mutableMapOf<String, Char>()
    private val antennas = mutableSetOf<Char>()
    private val antinodes = mutableSetOf<String>()
    private var maxX = 0
    private var maxY = 0
    private lateinit var xRange: IntRange
    private lateinit var yRange: IntRange

    override fun setup() {
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                grid.putIfAbsent("$x#$y", c)
                if (c != '.') antennas.add(c)
            }
            maxX = line.length - 1
            maxY = y
        }
        xRange = IntRange(0, maxX)
        yRange = IntRange(0, maxY)
        super.setup()
    }

    override fun partA(): String {
        println("grid count: ${grid.size}")
        println("0s: ${grid.map { it.value }.count { it == '0' }}")
        println("total antenna: ${antennas.size}")
        println("grid count: ${grid.size}")
        antennas.forEach { antenna ->
            val antennaLocations = grid.filter { it.value == antenna }.map { it.key }
            println("  ANTENNA locations for $antenna: $antennaLocations")
            println("combos are: ${combinationsOfTwo(antennaLocations)}")
            combinationsOfTwo(antennaLocations).forEach { couple ->
                findAntinodes(couple[0], couple[1])
            }
        }
        println("ANTINOCES: $antinodes")
        return antinodes.count().toString()
    }

    private fun findAntinodes(first: String, second: String) {
        val (x1, y1) = first.split("#").map { it.toInt() }
        val (x2, y2) = second.split("#").map { it.toInt() }
        val dx = abs(x2 - x1)
        val dy = abs(y2 - y1)
        val leftX = min(x1, x2)
        val rightX = max(x1, x2)
        val x0 = leftX - dx
        val x3 = rightX + dx
        val y0 = if (leftX == x1) {
            if (y1 < y2) y1 - dy else y1 + dy
        } else {
            // leftx == x2
            if (y2 < y1) y2 - dy else y2 + dy
        }
        val y3 = if (leftX == x1) {
            if (y1 < y2) y2 + dy else y2 - dy
        } else {
            // leftx == x2
            if (y2 < y1) y1 + dy else y1 - dy
        }
        println("finding antinodes for $first and $second")
        if (xRange.contains(x0) && yRange.contains(y0)) {
            println("     adding $x0#$y0")
            antinodes.add("$x0#$y0")
        }
        if (xRange.contains(x3) && yRange.contains(y3)) {
            println("     adding $x3#$y3")
            antinodes.add("$x3#$y3")
        }
    }

    fun <T> combinationsOfTwo(list: List<T>): List<List<T>> {
        val result = mutableListOf<List<T>>()

        for (i in 0 until list.size - 1) {
            for (j in i + 1 until list.size) {
                result.add(listOf(list[i], list[j]))
            }
        }

        return result
    }
}