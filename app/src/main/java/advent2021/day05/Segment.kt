package advent2021.day05

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
        if (isOrtho()) {
            (Math.min(startPoint.x, endPoint.x)..Math.max(startPoint.x, endPoint.x)).forEach { x ->
                (Math.min(startPoint.y, endPoint.y)..Math.max(
                    startPoint.y,
                    endPoint.y
                )).forEach { y ->
                    allPoints.add(Point(x, y))
                }
            }
        } else {
            val dx = if (endPoint.x > startPoint.x) 1 else -1
            val dy = if (endPoint.y > startPoint.y) 1 else -1
            var transientPoint = Point(startPoint)
            allPoints.add(startPoint)
            while (!transientPoint.equals(endPoint)) {
                transientPoint.offset(dx, dy)
                allPoints.add(Point(transientPoint))
            }
        }

        return allPoints
    }

    override fun toString(): String {
        return "($startPoint) -> ($endPoint)"
    }
}
