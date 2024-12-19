package advent2024.day19

import com.twelfthnightdj.advent2021.AocDays

class Y24D19 : AocDays() {
    override var dayId = 19
    private var towels = mutableListOf<String>()
    private var designs = mutableListOf<String>()

    override fun setup() {

        input.forEachIndexed { index, line ->
            when(index) {
                0 -> towels = line.split(", ").toMutableList()
                1 -> {}
                else -> {
                    designs.add(line)
                }
            }
        }
        super.setup()
    }

    override fun partA(): String {
        var total = 0
        designs.forEach { design ->
            if (checkForDesign(design)) total++.also { println("$design is possible") }
        }
        return total.toString()
    }

    fun checkForDesign(design: String): Boolean {
        towels.forEachIndexed { index, towel ->
            if (design.indexOf(towel) == 0) {
                if (design.length == towel.length) return true
                if (checkForDesign(design.substring(towel.length))) return true
            }
        }
        return false
    }
}