package advent2023.day08

import com.twelfthnightdj.advent2021.AocDays
import utils.findLCM

class Y23D08 : AocDays() {
    override var dayId = 8
    var steps = ""
    var map = mutableMapOf<String, Pair<String, String>>()
    override fun reset() {
        map.clear()
    }

    override fun partA(): String {
        input.forEachIndexed { index, line ->
            if (index == 0) {
                steps = line
            } else if (line.isNotEmpty()) {
                var letters = "\\w+".toRegex().find(line)
                val node = mutableListOf<String>()
                while (letters != null) {
                    node.add(letters.value)
                    letters = letters.next()
                }
                map.putIfAbsent(node[0], Pair(node[1], node[2]))
            }
        }
        var currentNode = "AAA"
        var stepsTaken = 0L
        var currentIndex = 0
//        while (currentNode != "ZZZ") {
//            val currentStep = steps[currentIndex]
//            currentNode = if (currentStep == 'R') map[currentNode]!!.second else map[currentNode]!!.first
//            currentIndex = (currentIndex + 1) % steps.length
//            stepsTaken++
//        }
        return stepsTaken.toString()
    }

    override fun partB(): String {
        println("HELLO!")
        var starters = mutableListOf<String>()
        var firstZs = mutableListOf<Long>()
        input.forEachIndexed { index, line ->
            if (index == 0) {
                steps = line
            } else if (line.isNotEmpty()) {
                var letters = "\\w+".toRegex().find(line)
                val node = mutableListOf<String>()
                while (letters != null) {
                    val l = letters.value
                    if (l.last() == 'A') {
                        starters.add(l)
                        firstZs.add(0L)
                    }
                    node.add(l)
                    letters = letters.next()
                }
                map.putIfAbsent(node[0], Pair(node[1], node[2]))
            }
        }
        var stepsTaken = 0L
        var currentIndex = 0
        while (true) {
            val currentStep = steps[currentIndex]
            starters.forEachIndexed { index, currentNode ->
                val newNode = if (currentStep == 'R') map[currentNode]!!.second else map[currentNode]!!.first
                starters[index] = newNode

                if (newNode.last() == 'Z' && firstZs[index] == 0L) {
                    firstZs[index] = stepsTaken + 1
                }
            }
            currentIndex = (currentIndex + 1) % steps.length
            stepsTaken++
            if (firstZs.all { it > 0L }) {
                return firstZs.findLCM().toString()
            }
        }
        return stepsTaken.toString()
    }
}