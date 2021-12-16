package com.twelfthnightdj.advent2021.day15

import android.graphics.Point
import com.twelfthnightdj.advent2021.AocDays

class Day15 : AocDays() {

    override var dayId = 15

    private lateinit var field: MutableList<MutableList<Int>>
    var maxX = 0
    var maxY = 0
    var lowestRoute = Int.MAX_VALUE

    override fun partA(): String {
        field = processInput(trialInput)
        maxX = field.size
        maxY = field[0].size
        val targetX = maxX - 1
        val targetY = maxY - 1
        println("ipt: ${field.prettyPrint()}")
        startA(Point(0, 0), Point(targetX, targetY)).toString()
        return "$lowestRoute"
    }

    fun startA(current: Point, target: Point) {
        val isVisited = mutableSetOf<Point>()
        val pathList = mutableSetOf<Point>()
        route(current, target, isVisited, pathList)
    }

    fun countIt(points: MutableSet<Point>): Int {
//        println("counting: $points")
        var total = 0
        points.forEach { point ->
            total += field[point.x][point.y]
        }
//        println("returning: $total")
        return total
    }

    fun route(
        current: Point,
        target: Point,
        isVisited: MutableSet<Point>,
        localPathList: MutableSet<Point>
    ) {
        if (current == target) {
            localPathList.add(target)
            lowestRoute = minOf(lowestRoute, countIt(localPathList))
            localPathList.remove(target)
            return
        }
        if (countIt(localPathList) > lowestRoute) return

        isVisited.add(current)

        val nearPoints = surroundingPoints(current)
        if (nearPoints.contains(target)) {
            localPathList.add(current)
            route(target, target, isVisited, localPathList)
        } else {
            nearPoints.forEach {
                if (!isVisited.contains(it)) {
                    localPathList.add(it)
                    route(it, target, isVisited, localPathList)
                    localPathList.remove(it)
                }
            }
        }
        isVisited.remove(current)
    }

    fun surroundingPoints(point: Point): List<Point> {
        val result = mutableListOf<Point>()
        if (point.x > 0) {
            result.add(Point(point.x - 1, point.y))
        }
        if (point.x + 1 < maxX) {
            result.add(Point(point.x + 1, point.y))
        }
        if (point.y > 0) {
            result.add(Point(point.x, point.y - 1))
        }
        if (point.y + 1 < maxY) {
            result.add(Point(point.x, point.y + 1))
        }
        return result
    }

    fun processInput(ipt: List<String>) =
        ipt.map { it.toCharArray().map { it.digitToInt() }.toMutableList() }.toMutableList()

    private fun MutableList<MutableList<Int>>.prettyPrint() {
        println("x: ${this.size}")
        this.forEach {
            println(it.joinToString(""))
        }
    }
}
