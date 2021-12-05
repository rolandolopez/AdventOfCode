package com.twelfthnightdj.advent2021.day05

import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.InputHelpers

class Day5 : AocDays() {
    var segments = mutableListOf<Segment>()
    var grid = mutableMapOf<Int, MutableMap<Int, Int>>()
    override fun partA(): String {
        parseInput(input)
        return countGrid()
    }

    override fun partB(): String {
        parseInput(trialInput)

        return countGrid()
    }

    private fun parseInput(ipt: List<String>) {
        ipt.forEachIndexed { index, value ->
            val seg = Segment(value)
            if (seg.isOrtho()) segments.add(seg)
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

    val trialInput = InputHelpers.getListOfStringsFromFile("/day05trial.txt")
    val input = InputHelpers.getListOfStringsFromFile("/day05.txt")
}

