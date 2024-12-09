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
        antennas.forEach { antenna ->
            val antennaLocations = grid.filter { it.value == antenna }.map { it.key }
            combinationsOfTwo(antennaLocations).forEach { couple ->
                findAntinodesA(couple[0], couple[1])
            }
        }
        return antinodes.count().toString()
    }

    private fun findAntinodesA(first: String, second: String) {
        val (x1, y1) = first.split("#").map { it.toInt() }
        val (x2, y2) = second.split("#").map { it.toInt() }

        val dx = x2 - x1
        val dy = y2 - y1

        val x0 = x1 - dx
        val y0 = y1 - dy

        val x3 = x2 + dx
        val y3 = y2 + dy
        if (xRange.contains(x0) && yRange.contains(y0)) {
            antinodes.add("$x0#$y0")
        }
        if (xRange.contains(x3) && yRange.contains(y3)) {
            antinodes.add("$x3#$y3")
        }
    }

    private fun findAntinodesB(first: String, second: String) {
        val (x1, y1) = first.split("#").map { it.toInt() }
        val (x2, y2) = second.split("#").map { it.toInt() }

        val dx = x2 - x1
        val dy = y2 - y1

        var p = -1
        while (true) {
            p++
            val newX = x1 - (p * dx)
            val newY = y1 - (p * dy)
            if (xRange.contains(newX) && yRange.contains(newY)) {
                antinodes.add("$newX#$newY")
            } else {
                break
            }
        }
        p = -1
        while (true) {
            p++
            val newX = x2 + (p * dx)
            val newY = y2 + (p * dy)
            if (xRange.contains(newX) && yRange.contains(newY)) {
                antinodes.add("$newX#$newY")
            } else {
                break
            }
        }
    }

    override fun reset() {
        antinodes.clear()
        super.reset()
    }

    override fun partB(): String {
        antennas.forEach { antenna ->
            val antennaLocations = grid.filter { it.value == antenna }.map { it.key }
            combinationsOfTwo(antennaLocations).forEach { couple ->
                findAntinodesB(couple[0], couple[1])
            }
        }
        return antinodes.count().toString()
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