package advent2022.day05

import com.twelfthnightdj.advent2021.AocDays

class Y22D05 : AocDays() {
    override var dayId = 5

    val pods = mutableMapOf<Int, MutableList<String>>()

    override fun partA(): String {
        var instructionsReached = false
        input.forEach { line ->
            if (instructionsReached) {
                val instr =
                    line.replace("move.", "").replace("from.", "").replace("to.", "").split(".")
                        .map { it.toInt() }
                moveA(instr[0], instr[1], instr[2])
            } else {
                if (line.isEmpty()) {
                    instructionsReached = true
                } else {
                    line.forEachIndexed { index, c ->
                        if (c.isUpperCase()) {
                            val podSpot = (index / 4) + 1
                            if (pods.containsKey(podSpot)) {
                                pods[podSpot]?.add(c.toString())
                            } else {
                                pods[podSpot] = mutableListOf(c.toString())
                            }
                        }
                    }
                }
            }
        }
        var answer = ""
        repeat(pods.size) {
            answer += pods[(it + 1)]?.first()
        }

        return answer
    }

    private fun moveA(boxCount: Int, from: Int, to: Int) {
        repeat(boxCount) { _ ->
            pods[from]?.first()?.let {
                pods[from]?.removeFirst()
                pods[to]?.add(0, it)
            }
        }
    }

    private fun moveB(boxCount: Int, from: Int, to: Int) {
        val moving = pods[from]?.subList(0, boxCount)?.toMutableList()
        repeat(boxCount) { pods[from]?.removeFirst() }
        moving?.let { boxes ->
            pods[to]?.addAll(0, boxes)
        }

    }

    override fun reset() {
        pods.clear()
    }

    override fun partB(): String {
        var instructionsReached = false
        input.forEach { line ->
            if (instructionsReached) {
                val instr =
                    line.replace("move.", "").replace("from.", "").replace("to.", "").split(".")
                        .map { it.toInt() }
                moveB(instr[0], instr[1], instr[2])
            } else {
                if (line.isEmpty()) {
                    instructionsReached = true
                } else {
                    line.forEachIndexed { index, c ->
                        if (c.isUpperCase()) {
                            val podSpot = (index / 4) + 1
                            if (pods.containsKey(podSpot)) {
                                pods[podSpot]?.add(c.toString())
                            } else {
                                pods[podSpot] = mutableListOf(c.toString())
                            }
                        }
                    }
                }
            }
        }
        var answer = ""
        repeat(pods.size) {
            answer += pods[(it + 1)]?.first()
        }

        return answer
    }
}