package advent2021.day12

import android.util.MutableBoolean
import com.twelfthnightdj.advent2021.AocDays

class Y21D12 : AocDays() {
    override var dayId = 12

    private var caves = mutableMapOf<String, Cave>()
    private var pathCounter = 0
    private var doubleVisit = MutableBoolean(false)

    override fun partA(): String {
        processInput(input)
        val startCave = caves.values.first { it.name == "start" }
        val endCave = caves.values.first { it.name == "end" }
        printAllPathsA(startCave, endCave)
        return "$pathCounter"
    }

    override fun reset() {
        pathCounter = 0
        caves.clear()
    }

    @ExperimentalStdlibApi
    override fun partB(): String {

        return "${calculateAll(parse(input), canVisitTwice = true)}"
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
        printAllPathsUtilB(s, d, isVisited, pathList, doubleVisit)
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
        hasVisitedTwice: MutableBoolean
    ) {
        if (u == d) {
            println("list: $localPathList")
            pathCounter++
        }
        if (!u.canRevisit) {
            isVisited.add(u)
        }
        u.connections.forEach { connectedCave ->
            if (!isVisited.contains(connectedCave) || (isVisited.contains(connectedCave) && !hasVisitedTwice.value)) {
                println("connectCave: $connectedCave")
                println(
                    "${!isVisited.contains(connectedCave)} || (${
                        isVisited.contains(
                            connectedCave
                        )
                    } && ${!hasVisitedTwice.value})"
                )
                if (isVisited.contains(connectedCave)) {
                    hasVisitedTwice.value = true
                }

                localPathList.add(connectedCave)

                printAllPathsUtilB(connectedCave, d, isVisited, localPathList, hasVisitedTwice)

                val last = localPathList.removeLast()
                if (!last.canRevisit && localPathList.contains(last)) {
                    println("changing hasVisitedTwice back to false last: $last path: $localPathList")
                    hasVisitedTwice.value = false
                }
//                localPathList.remove(connectedCave)
            }
        }
        isVisited.remove(u)
    }

    @ExperimentalStdlibApi
    fun parse(ipt: List<String>) = buildMap<String, Iterable<String>> {
        ipt.map { it.split("-") }
            .forEach { (v1, v2) ->
                compute(v1) { _, l -> (l ?: hashSetOf()) + v2 }
                compute(v2) { _, l -> (l ?: hashSetOf()) + v1 }
            }
    }

    private fun calculateAll(
        edges: Map<String, Iterable<String>>,
        current: String = "start",
        visited: Collection<String> = hashSetOf(current),
        canVisitTwice: Boolean = false
    ): Int = edges[current]!!.sumOf { next ->
        when {
            next == "end" -> 1
            next.first().isUpperCase() -> calculateAll(
                edges,
                next,
                visited + current,
                canVisitTwice
            )
            next !in visited -> calculateAll(edges, next, visited + current, canVisitTwice)
            canVisitTwice && next != "start" -> calculateAll(edges, next, visited + current, false)
            else -> 0
        }
    }

    enum class VisitedStatus {
        NEVER,
        ONCE,
        DONE
    }
}
