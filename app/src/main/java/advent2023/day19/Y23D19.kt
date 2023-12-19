package advent2023.day19

import com.twelfthnightdj.advent2021.AocDays

class Y23D19 : AocDays() {
    override var dayId = 19
    var partSection = false
    var instructions = mutableMapOf<String, Instruction>()
    var parts = mutableSetOf<Part>()

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


    inner class Instruction (var name: String, var seed: String) {
        var innerList = mutableListOf<String>()
        init {
            seed.split(",").map {
                innerList.add(it)
            }
        }

        fun evaluate(part: Part): Boolean {
            val regex =	"(.)(<|>)(\\d+):(.*)".toRegex()
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
                } else
                    {
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
