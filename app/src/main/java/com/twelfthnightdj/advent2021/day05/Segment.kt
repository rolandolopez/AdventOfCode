package com.twelfthnightdj.advent2021.day05

import android.graphics.Point
import kotlin.properties.Delegates

class Segment (seed: String) {
    private var startPoint by Delegates.notNull<Point>()
    private var endPoint by Delegates.notNull<Point>()

    init {
        val (start, end) = seed.split(" -> ")
        startPoint = Point(start.split(",")[0].toInt(), start.split(",")[1].toInt())
        endPoint = Point(end.split(",")[0].toInt(), end.split(",")[1].toInt())
    }

    fun isOrtho() = ((startPoint.x == endPoint.x) || (startPoint.y == endPoint.y))

    fun allPoints(): MutableList<Point> {
        var allPoints = mutableListOf<Point>()
        (Math.min(startPoint.x, endPoint.x)..Math.max(startPoint.x, endPoint.x)).forEach { x ->
            (Math.min(startPoint.y, endPoint.y)..Math.max(startPoint.y, endPoint.y)).forEach { y ->
                allPoints.add(Point(x, y))
            }
        }
        return allPoints
    }

    override fun toString(): String {
        return "($startPoint) -> ($endPoint)"
    }
}
