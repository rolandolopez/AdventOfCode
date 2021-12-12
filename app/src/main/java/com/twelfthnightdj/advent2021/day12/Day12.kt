package com.twelfthnightdj.advent2021.day12

import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.InputHelpers

class Day12 : AocDays() {

    var caves = mutableMapOf<String, Cave>()
    var pathCounter = 0

    override fun partA(): String {
        processInput(input)
        val startCave = caves.values.filter { it.name == "start" }.first()
        val endCave = caves.values.filter { it.name == "end" }.first()
        printAllPaths(startCave, endCave)
        return "$pathCounter"
    }

    private fun processInput(ipt: List<String>) {
        ipt.forEach { line ->
            val (first, second) = line.split("-")
            val firstCave = caves.getOrDefault(first, Cave(first))
            val secondCave = caves.getOrDefault(second, Cave(second))
            firstCave.addConnection(secondCave)
            secondCave.addConnection(firstCave)
            caves.put(first, firstCave)
            caves.put(second, secondCave)
        }
    }

    private fun printAllPaths(s: Cave, d: Cave) {
        var isVisited = mutableSetOf<Cave>()
        var pathList = mutableListOf<Cave>()
        pathList.add(s)
        printAllPathsUtil(s, d, isVisited, pathList)
    }

    private fun printAllPathsUtil(
        u: Cave,
        d: Cave,
        isVisited: MutableSet<Cave>,
        localPathList: MutableList<Cave>
    ) {
        if (u == d) {
            pathCounter++
        }
        if (!u.canRevisit) {
            isVisited.add(u)
        }
        u.connections.forEach { connectedCave ->
            if (!isVisited.contains(connectedCave)) {
                localPathList.add(connectedCave)
                printAllPathsUtil(connectedCave, d, isVisited, localPathList)

                localPathList.remove(connectedCave)
            }
        }
        isVisited.remove(u)
    }

    val trialInput = InputHelpers.getListOfStringsFromFile("/day12trial.txt")
    private val input = InputHelpers.getListOfStringsFromFile("/day12.txt")
}
