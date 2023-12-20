package advent2023.day19

import com.twelfthnightdj.advent2021.AocDays

class Y23D19 : AocDays() {
    override var dayId = 19
    var partSection = false
    var instructions = mutableMapOf<String, Instruction>()
    var parts = mutableSetOf<Part>()
    var allX = mutableListOf<Int>()
    var allM = mutableListOf<Int>()
    var allA = mutableListOf<Int>()
    var allS = mutableListOf<Int>()

    override fun setup() {
        input.forEach { line ->
            if (line.isEmpty()) {
                partSection = true
                return@forEach
            }
            if (partSection) {
                parts.add(Part(line))
            } else {
                val n = line.substring(0, line.indexOf("{"))
                val instr = line.substring(line.indexOf("{") + 1, line.length - 1)
                instructions.putIfAbsent(n, Instruction(n, instr))
            }
        }
    }

    override fun partA(): String {
        return parts.filter { instructions["in"]!!.evaluate(it) }.sumOf { it.total() }.toString()
    }

    override fun partB(): String {
        (1..4000).forEach {
            allX.add(it)
            allM.add(it)
            allA.add(it)
            allS.add(it)
        }

        instructions.values.forEach {
            it.evaluateB()
        }

        return (allX.count().toLong() * allM.count().toLong() * allA.count().toLong() * allS.count().toLong()).toString()

    }

    inner class Instruction(var name: String, var seed: String) {
        var innerList = mutableListOf<String>()
        val regex = "(.)(<|>)(\\d+):(.*)".toRegex()

        init {
            seed.split(",").map {
                innerList.add(it)
            }
        }

        //        s>2770:qs,m<1801:hdj,R
        fun evaluateB() {
            innerList.forEachIndexed { index, instr ->
                if (instr.contains(":")) {
                    val matches = regex.find(instr)!!.groupValues
                    if (matches[2] == "<") {
                        when (matches[1]) {
                            "x" -> allX.removeIf { it >= matches[3].toInt() }.also { println("removing x") }
                            "m" -> allM.removeIf { it >= matches[3].toInt() }.also { println("removing m") }
                            "a" -> allA.removeIf { it >= matches[3].toInt() }.also { println("removing a") }
                            "s" -> allS.removeIf { it >= matches[3].toInt() }.also { println("removing s") }
                        }
                    } else {
                        when (matches[1]) {
                            "x" -> allX.removeIf { it <= matches[3].toInt() }.also { println("removing x") }
                            "m" -> allM.removeIf { it <= matches[3].toInt() }.also { println("removing m") }
                            "a" -> allA.removeIf { it <= matches[3].toInt() }.also { println("removing a") }
                            "s" -> allS.removeIf { it <= matches[3].toInt() }.also { println("removing s") }

                        }
                    }
                }
            }
        }

        fun evaluate(part: Part): Boolean {

            innerList.forEachIndexed { index, instr ->
                if (instr == "A") return true
                if (instr == "R") return false
                if (!instr.contains(":")) {
                    return instructions[instr]?.evaluate(part) ?: false
                }
                val matches = regex.find(instr)!!.groupValues

                if (matches[2] == "<") {
                    if (part.get(matches[1]) < matches[3].toInt()) {
                        return when (matches[4]) {
                            "A" -> true
                            "R" -> false
                            else -> instructions[matches[4]]!!.evaluate(part)
                        }
                    } else {
                        return@forEachIndexed
                    }
                } else {
                    if (part.get(matches[1]) > matches[3].toInt()) {
                        return when (matches[4]) {
                            "A" -> true
                            "R" -> false
                            else -> instructions[matches[4]]!!.evaluate(part)
                        }
                    }
                }
            }
            return false
        }

    }

}
