package advent2022.day05

import com.twelfthnightdj.advent2021.AocDays
import java.util.*
import kotlin.collections.AbstractCollection
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap

class Y22D05 : AocDays() {
    override var dayId = 5

    override fun partA(): String {
        val pods = mutableMapOf<Int, ArrayDeque<String>>()
        var instructionsReached = false
        input.forEach { line ->
            if (instructionsReached) {
                val instr = line.replace("move.", "").replace("from.", "").replace("to.", "").split(".").map { it.toInt() }
                repeat(instr[0]) { _ ->
                    pods[instr[1]]?.first()?.let {
                        pods[instr[1]]?.removeFirst()
                        pods[instr[2]]?.add(0, it)
                    }
                }
            } else {
                if (line.isEmpty()) {
                    instructionsReached = true
                } else {
                    line.forEachIndexed { index, c ->
                        if (c.isUpperCase()) {
                            val podSpot = (index/4) + 1
                            if (pods.containsKey(podSpot)) {
                                pods[podSpot]?.addLast(c.toString())
                            } else {
                                val ad = ArrayDeque<String>()
                                ad.add(c.toString())
                                pods[podSpot] = ad
                            }
                        }
                    }
                }
            }
        }
        println("pods: $pods")
        var answer = ""
        repeat(pods.size) {
            answer += pods[(it + 1)]?.first()
        }

        return answer
    }

    override fun partB(): String {
        return super.partB()
    }
}