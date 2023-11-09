package com.twelfthnightdj.advent2021.day06

import com.twelfthnightdj.advent2021.AocDays

class Y21D06 : AocDays() {
    override var dayId = 6

    override fun partA(): String {
        val seed = inputAsString.split(",") ?: emptyList()
        return  start(seed.map { it.toInt() })
    }

    override fun partB(): String {
        val seed = inputAsString.split(",") ?: emptyList()
        return fazio(seed.map { it.toInt() }, 256)
    }

    private fun start(seed: List<Int>, days: Int = 80): String {
        val aquarium = mutableListOf<Fish>()
        aquarium.addAll(seed.map { Fish(it) })
        repeat(days) { day ->
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

    private fun fazio(seed: List<Int>, days: Int = 80): String {
        val fishCounts = (0..8).map { ind -> seed.count { it == ind }.toLong() }
        val resultFish = (1..days).fold(fishCounts) { counts, _ ->
            val newFish = counts[0]
            counts.drop(1).toMutableList().also {
                it[6] += newFish
            } + newFish
        }
        return resultFish.sum().toString()
    }
}
