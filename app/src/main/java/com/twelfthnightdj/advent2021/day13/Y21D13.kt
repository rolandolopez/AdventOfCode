package com.twelfthnightdj.advent2021.day13

import android.graphics.Point
import com.twelfthnightdj.advent2021.AocDays

class Y21D13 : AocDays() {
    override var dayId = 13

    private var points = mutableListOf<Point>()
    private var folds = mutableListOf<Pair<String, Int>>()
    override fun partA(): String {
        process(input)
        processFold(1)
        return "${points.size}"
    }

    override fun reset() {
        points.clear()
        folds.clear()
    }

    override fun partB(): String {
        process(input)
        processFold()
        val maxX = points.maxByOrNull { it.x }!!.x
        val maxY = points.maxByOrNull { it.y }!!.y
        val graph: MutableList<MutableList<String>> = MutableList(maxY + 1) { MutableList(maxX + 1) { "." } }
        points.forEach {
            graph[it.y][it.x] = "#"
        }
        println("graph:")
        println("${graph.prettyPrint()}")
        return "See Log Print Out EBLUBRFH"
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
    private fun MutableList<MutableList<String>>.prettyPrint() {
        println("x: ${this.size}")
        this.forEach {
            println(it.joinToString(""))
        }
    }
}
