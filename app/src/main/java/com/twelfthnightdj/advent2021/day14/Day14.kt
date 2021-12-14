package com.twelfthnightdj.advent2021.day14

import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.InputHelpers

class Day14 : AocDays() {
    private var seed = mutableListOf<String>()
    private var injections = mutableMapOf<String, String>()

    override fun partA(): String {
        process(input)
        repeat(10) {
            val newSeed = mutableListOf<String>()
            seed.zipWithNext { a, b ->
                if (newSeed.isEmpty()) {
                    newSeed.add(a)
                }
                newSeed.add(injections.getOrDefault(a + b, ""))
                newSeed.add(b)
            }
            seed = newSeed
        }
        seed.groupBy { it }
        val ordered = seed.groupBy { it }.values.sortedByDescending { it.size }.sortedByDescending { it.size }
        val result = ordered.first().size - ordered.last().size
        return "$result"
    }

    private fun process(ipt: List<String>) {
        ipt.forEachIndexed { index, line ->
            when {
                index == 0 -> seed = line.toCharArray().map { it.toString() }.toMutableList()
                line.isEmpty() -> {
                }
                else -> addInjection(line)
            }
        }
    }

    private fun addInjection(line: String) {
        val(first, second) = line.split(" -> ")
        injections[first] = second
    }



    val trialInput = InputHelpers.getListOfStringsFromFile("/day14trial.txt")
    private val input = InputHelpers.getListOfStringsFromFile("/day14.txt")
}
