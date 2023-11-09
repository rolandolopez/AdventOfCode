package com.twelfthnightdj.advent2021.day05

import com.twelfthnightdj.advent2021.AocDays

class Y21D05 : AocDays() {
    override var dayId = 5
    var segments = mutableListOf<Segment>()
    var grid = mutableMapOf<Int, MutableMap<Int, Int>>()
    override fun partA(): String {
        parseInputA(input)
        return countGrid()
    }

    override fun reset() {
        segments.clear()
        grid.clear()
    }

    override fun partB(): String {
        parseInputB(input)

        return countGrid()
    }

    private fun parseInputA(ipt: List<String>) {
        ipt.forEachIndexed { index, value ->
            val seg = Segment(value)
            if (seg.isOrtho()) segments.add(seg)
        }
        segments.forEach { segment ->
            markGrid(segment)
        }
    }

    private fun parseInputB(ipt: List<String>) {
        ipt.forEachIndexed { index, value ->
            val seg = Segment(value)
            segments.add(seg)
        }
        segments.forEach { segment ->
            markGrid(segment)
        }
    }

    fun countGrid():String {
        var dangerCount = 0
        grid.values.forEach {
            dangerCount += it.values.filter { count ->
                count > 1
            }.size
        }
        return "$dangerCount"
    }

    private fun markGrid(line: Segment) {
        val allPoints = line.allPoints()
        allPoints.forEach { point ->
            grid.putIfAbsent(point.x, mutableMapOf())
            val xFile = grid.getOrDefault(point.x, mutableMapOf())
            xFile.put(point.y, xFile.getOrDefault(point.y, 0) + 1)
        }
    }
}

