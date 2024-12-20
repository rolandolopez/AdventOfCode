package advent2024.day19

import com.twelfthnightdj.advent2021.AocDays
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Y24D19 : AocDays() {
    override var dayId = 19
    private var towels = mutableListOf<String>()
    private var designs = mutableListOf<String>()
    private var total = 0

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
        designs.forEach { design ->
            if (checkForDesignA(design)) total++
        }
        return total.toString()
    }

    override fun reset() {
        total = 0
    }

    fun checkForDesignA(design: String): Boolean {
        towels.forEachIndexed { index, towel ->
            if (design.indexOf(towel) == 0) {
                if (design.length == towel.length) return true
                if (checkForDesignA(design.substring(towel.length))) return true
            }
        }
        return false
    }

    override fun partB(): String {
        GlobalScope.launch {
            designs.forEach { design ->
                checkForDesignB(design)
                println("completed check for $design with total so far: $total")
            }
            println("total for part B: $total")
        }
        return total.toString()
    }

    fun checkForDesignB(design: String){
        towels.forEachIndexed { index, towel ->
            if (design.indexOf(towel) == 0) {
                if (design.length == towel.length) {
                    total++
                    return
                }
                checkForDesignB(design.substring(towel.length))
            }
        }
    }
}