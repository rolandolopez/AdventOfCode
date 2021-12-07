package com.twelfthnightdj.advent2021.day07

import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.InputHelpers
import kotlin.math.abs

class Day7 : AocDays() {
    var points = emptyList<Int>()
    var possibleAnswer = 0
    val eps = .001
    val diffs = listOf(-1, 1)

    override fun partA(): String {
        points = input?.split(",")?.map { it.toInt() } ?: emptyList()
        possibleAnswer = kotlin.math.floor(points.average()).toInt()
        goA()

        println("position : $possibleAnswer")
        println("fuel cost: ${distanceA(possibleAnswer)}")

        return distanceA(possibleAnswer).toString()

    }

    fun goA() {
        var dist = distanceA(possibleAnswer)
        var step = 100
        var done: Boolean

        while(step > eps) {
            done = false
            diffs.forEach diffs@ { diff ->
                val nx = possibleAnswer + (step * diff)
                val t = distanceA(nx)

                if (t < dist) {
                    dist = t
                    possibleAnswer = nx
                    done = true
                    return@diffs
                }
            }

            if (!done) {
                step /= 2
            }
        }
    }

    private fun distanceA(x: Int): Int {
        var sum = 0
        points.forEach {
            sum += abs(it - x)
        }
        return sum
    }

    val trialInput = InputHelpers.getContentsFromFile("/day07trial.txt")
    val input = InputHelpers.getContentsFromFile("/day07.txt")
}
