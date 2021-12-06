package com.twelfthnightdj.advent2021.day06

import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.InputHelpers

class Day6 : AocDays() {
    override fun partA(): String {
        val seed = input?.split(",") ?: emptyList()
        return start(seed.map { it.toInt() })
    }


    private fun start(seed: List<Int>, days: Int = 80): String {
        val aquarium = mutableListOf<Fish>()
        aquarium.addAll(seed.map { Fish(it) })
        repeat(days) {
            var newFishes = 0
            aquarium.forEach { fish ->
                if (fish.nextDay()) {
                    newFishes++
                }
            }
            repeat(newFishes) {
                aquarium.add(Fish())
            }
        }
        return aquarium.count().toString()
    }


    val trialInput = InputHelpers.getContentsFromFile("/day06trial.txt")
    val input = InputHelpers.getContentsFromFile("/day06.txt")
}
