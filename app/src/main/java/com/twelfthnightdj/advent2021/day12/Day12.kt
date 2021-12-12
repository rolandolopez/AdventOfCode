package com.twelfthnightdj.advent2021.day12

import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.InputHelpers

class Day12 : AocDays() {

    var caves = mutableMapOf<String, Cave>()

    override fun partA(): String {
        processInput(trialInput)
        println("caves: $caves")
        return ""
    }

    private fun processInput(ipt: List<String>) {
        ipt.forEach { line ->
            val (first, second) = line.split("-")
            println("first: $first, second: $second")
            val firstCave = caves.getOrDefault(first, Cave(first))
            val secondCave = caves.getOrDefault(second, Cave(second))
            println("firstCave: $firstCave")
            firstCave.addConnection(secondCave)
            secondCave.addConnection(firstCave)
            caves.put(first, firstCave)
            caves.put(second, secondCave)
        }
    }

    val trialInput = InputHelpers.getListOfStringsFromFile("/day12trial.txt")
    private val input = InputHelpers.getListOfStringsFromFile("/day12.txt")
}
