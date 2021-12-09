package com.twelfthnightdj.advent2021.day09

import android.graphics.Point
import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.InputHelpers

class Day9 : AocDays() {
    lateinit var sanitized: List<List<Int>>
    private var safeSpots = mutableListOf<Int>()

    override fun partA(): String {
        sanitized = processInput(trialInput)
        val maxX = sanitized.size
        val maxY = sanitized[0].size
        sanitized.forEachIndexed { x, list ->
            list.forEachIndexed { y, value ->
                if (isLowestAround(x, y, maxX, maxY)) {
                    safeSpots.add(value)
                }
            }

        }
        val risk = safeSpots.sum() + safeSpots.size
        return "$risk"
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


    val trialInput = InputHelpers.getListOfStringsFromFile("/day09trial.txt")
    val input = InputHelpers.getListOfStringsFromFile("/day09.txt")
}
