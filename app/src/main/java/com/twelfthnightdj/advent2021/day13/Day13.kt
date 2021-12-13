package com.twelfthnightdj.advent2021.day13

import android.graphics.Point
import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.InputHelpers

class Day13 : AocDays() {
    var points = mutableListOf<Point>()
    var folds = mutableListOf<Pair<String, Int>>()
    override fun partA(): String {
        process(input)
        processFold(1)
        return "${points.size}"
    }

    private fun processFold(count: Int = folds.size) {
        repeat(count) {
            val fold = folds.removeAt(0)
            when(fold.first) {
                "x" -> foldLeft(fold.second)
                "y" -> foldUp(fold.second)
            }
        }
    }

    private fun foldUp(y: Int) {
        points = points.map {
            if (it.y > y) {
                Point(it.x, it.y - (2 * (it.y - y)))
            } else it
        }.distinct().toMutableList()
    }

    private fun foldLeft(x: Int) {
        points = points.map {
            if (it.x > x) {
                Point(it.x - (2 * (it.x - x)), it.y)
            } else it
        }.distinct().toMutableList()
    }


    private fun process(ipt: List<String>) {
        ipt.forEach { line -> when {
            line.startsWith("fold along ") -> addFold(line)
            line.trim().isEmpty() -> {}
            else -> addPoint(line)
        } }
    }

    private fun addFold(line: String) {
        val (axis, value) = line.removePrefix("fold along ").split("=")
        folds.add(Pair(axis, value.toInt()))
    }
    private fun addPoint(line: String) {
        val (x, y) = line.split(",").map { it.toInt() }
        points.add(Point(x, y))
    }

    val trialInput = InputHelpers.getListOfStringsFromFile("/day13trial.txt")
    private val input = InputHelpers.getListOfStringsFromFile("/day13.txt")

}
