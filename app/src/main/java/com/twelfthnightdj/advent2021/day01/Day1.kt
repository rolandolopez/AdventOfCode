package com.twelfthnightdj.advent2021.day01

import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.InputHelpers


class Day1 : AocDays() {

    override fun partA(): String {
        return countIt(localInput).toString()
    }

    override fun partB(): String {
        val mappedInput = localInput.mapIndexed { index, value ->
            val second = if ((index + 1) >= localInput.size) 0 else localInput[index + 1]
            val third = if ((index + 2) >= localInput.size) 0 else localInput[index + 2]
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

    val localInput = input.map { it.toInt() }
}
