package com.twelfthnightdj.advent2021.day09

import android.graphics.Point
import com.twelfthnightdj.advent2021.AocDays

class Y21D09 : AocDays() {
    override var dayId = 9

    private lateinit var sanitized: List<List<Int>>
    private var safeSpots = mutableListOf<Int>()
    private var safePoints = mutableListOf<Point>()
    private var basins: MutableMap<Point, MutableSet<Point>> = mutableMapOf()

    override fun partA(): String {
        sanitized = processInput(input)
        val maxX = sanitized.size
        val maxY = sanitized[0].size
        sanitized.forEachIndexed { x, list ->
            list.forEachIndexed { y, value ->
                if (isLowestAround(x, y, maxX, maxY)) {
                    safeSpots.add(value)
                    safePoints.add(Point(x, y))
                }
            }

        }
        val risk = safeSpots.sum() + safeSpots.size
        return "$risk"
    }

    override fun partB(): String {
        val maxX = sanitized.size
        val maxY = sanitized[0].size
        safePoints.forEach { basins.putIfAbsent(it, mutableSetOf()) }
        basins.keys.forEach { point ->
            checkForDrainage(point, point, maxX, maxY)
        }
        var result = 1
        basins.values.map { it.size }.sortedDescending().take(3).forEach { result *= it }
        return "$result"
    }

    private fun checkForDrainage(keyPoint: Point, point: Point, maxX: Int, maxY: Int) {
        if (basins.getOrDefault(keyPoint, mutableSetOf()).contains(point)) {
            return
        }
        if (sanitized[point.x][point.y] != 9) {
            basins.getOrDefault(keyPoint, mutableSetOf()).add(point)
        }
        if (point.x > 0) {
            if (sanitized[point.x - 1][point.y] != 9) {
                checkForDrainage(keyPoint, Point(point.x - 1, point.y), maxX, maxY)
            }
        }
        if (point.x + 1 < maxX) {
            if (sanitized[point.x + 1][point.y] != 9) {
                checkForDrainage(keyPoint, Point(point.x + 1, point.y), maxX, maxY)
            }
        }
        if (point.y > 0) {
            if (sanitized[point.x][point.y - 1] != 9) {
                checkForDrainage(keyPoint, Point(point.x, point.y - 1), maxX, maxY)
            }
        }
        if (point.y + 1 < maxY) {
            if (sanitized[point.x][point.y + 1] != 9) {
                checkForDrainage(keyPoint, Point(point.x, point.y + 1), maxX, maxY)
            }
        }
    }

    private fun isLowestAround(x: Int, y: Int, maxX: Int, maxY: Int): Boolean {
        val comps = mutableListOf(sanitized[x][y])
        if (x > 0) {
            comps.add(sanitized[x-1][y])
        }
        if (x + 1 < maxX) {
            comps.add(sanitized[x+1][y])
        }
        if (y > 0) {
            comps.add(sanitized[x][y-1])
        }
        if (y + 1 < maxY) {
            comps.add(sanitized[x][y+1])
        }
        (1 until comps.size).forEach {
            if (comps[0] >= comps[it]) return false
        }
        return true
    }
    private fun processInput(ipt: List<String>) = ipt.map{ it.toCharArray().map { it.digitToInt() }}
}
