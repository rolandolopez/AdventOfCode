package advent2023.day08

import com.twelfthnightdj.advent2021.AocDays

class Y23D08 : AocDays() {
    override var dayId = 8
    var steps = ""
    var map = mutableMapOf<String, Pair<String, String>>()

    override fun partA(): String {
        input.forEachIndexed { index, line ->
            if (index == 0) {
                steps = line
            } else if (line.isNotEmpty()) {
                var letters = "\\w+".toRegex().find(line)
                var node = mutableListOf<String>()
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
        while (currentNode != "ZZZ") {
            val currentStep = steps[currentIndex]
            currentNode = if (currentStep == 'R') map[currentNode]!!.second else map[currentNode]!!.first
            currentIndex = (currentIndex + 1) % steps.length
            stepsTaken++
        }
        return stepsTaken.toString()
    }

    override fun partB(): String {
        return super.partB()
    }
}