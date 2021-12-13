package com.twelfthnightdj.advent2021.day12

import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.InputHelpers

class Day12 : AocDays() {

    private var caves = mutableMapOf<String, Cave>()
    private var pathCounter = 0

    override fun partA(): String {
        processInput(trialInput)
        val startCave = caves.values.first { it.name == "start" }
        val endCave = caves.values.first { it.name == "end" }
        printAllPathsA(startCave, endCave)
        return "$pathCounter"
    }

    override fun reset() {
        pathCounter = 0
        caves.clear()
    }

    override fun partB(): String {
        processInput(trialInput)
        val startCave = caves.values.first { it.name == "start" }
        val endCave = caves.values.first { it.name == "end" }
        printAllPathsB(startCave, endCave)
        return "$pathCounter"
    }

    private fun processInput(ipt: List<String>) {
        ipt.forEach { line ->
            val (first, second) = line.split("-")
            val firstCave = caves.getOrDefault(first, Cave(first))
            val secondCave = caves.getOrDefault(second, Cave(second))
            if (secondCave.name != "start") {
                if (firstCave.name != "end") {
                    firstCave.addConnection(secondCave)
                }
            }
            if (firstCave.name != "start") {
                if (secondCave.name != "end") {
                    secondCave.addConnection(firstCave)
                }
            }
            caves[first] = firstCave
            caves[second] = secondCave
        }
    }

    private fun printAllPathsA(s: Cave, d: Cave) {
        val isVisited = mutableSetOf<Cave>()
        val pathList = mutableListOf<Cave>()
        pathList.add(s)
        printAllPathsUtilA(s, d, isVisited, pathList)
    }

    private fun printAllPathsB(s: Cave, d: Cave) {
        val isVisited = mutableSetOf<Cave>()
        val pathList = mutableListOf<Cave>()
        var hasVisitedTwice = false
        pathList.add(s)
        println("caves: $caves: $pathList")
        printAllPathsUtilB(s, d, isVisited, pathList, false)
    }

    private fun printAllPathsUtilA(
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
                printAllPathsUtilA(connectedCave, d, isVisited, localPathList)

                localPathList.remove(connectedCave)
            }
        }
        isVisited.remove(u)
    }

    private fun printAllPathsUtilB(
        u: Cave,
        d: Cave,
        isVisited: MutableSet<Cave>,
        localPathList: MutableList<Cave>,
        hasVisitedTwice: Boolean
    ) {
        if (u == d) {
            println("list: $localPathList")
            pathCounter++
        }
        if (!u.canRevisit) {
            isVisited.add(u)
        }
        u.connections.forEach { connectedCave ->
            if (connectedCave.name == "c") {
                println("yes")
            }
            if (!isVisited.contains(connectedCave) || (isVisited.contains(connectedCave) && !hasVisitedTwice)) {
                println("connectCave: $connectedCave")
                println("${!isVisited.contains(connectedCave)} || (${isVisited.contains(connectedCave)} && ${!hasVisitedTwice})")
                var twice = hasVisitedTwice
                if (isVisited.contains(connectedCave)) {
                    twice = true
                }

                localPathList.add(connectedCave)

                printAllPathsUtilB(connectedCave, d, isVisited, localPathList, twice)

                localPathList.removeLast()
//                localPathList.remove(connectedCave)
            }
        }
        println("isVisited before: $isVisited")
        isVisited.remove(u)
        println("isVisited after: $isVisited")
    }

    val trialInput = InputHelpers.getListOfStringsFromFile("/day12trial.txt")
    private val input = InputHelpers.getListOfStringsFromFile("/day12.txt")

    enum class VisitedStatus {
        NEVER,
        ONCE,
        DONE
    }
}
