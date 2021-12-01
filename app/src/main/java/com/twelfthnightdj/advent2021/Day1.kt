package com.twelfthnightdj.advent2021

import com.twelfthnightdj.advent2021.util.InputHelpers


class Day1 : AocDays() {

    override fun partA(): String {
        return countIt(input).toString()
    }

    override fun partB(): String {
        val mappedInput = input.mapIndexed { index, value ->
            val second = if ((index + 1) >= input.size) 0 else input[index + 1]
            val third = if ((index + 2) >= input.size) 0 else input[index + 2]
            value + second + third
        }

        return countIt(mappedInput).toString()
    }

    fun countIt(ipt: List<Int>): Int {
        var counter = 0
        ipt.forEachIndexed { index, value ->
            if (index > 0) {
                val previous = ipt[index - 1]
                if (previous < value) counter++
            }
        }
        return counter
    }

    val trialInput = listOf(
        199,
        200,
        208,
        210,
        200,
        207,
        240,
        269,
        260,
        263
    )

    val input = InputHelpers.getListOfStringsFromFile("/day01.txt").map { it.toInt() }
}
