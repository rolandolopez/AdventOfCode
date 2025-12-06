package advent2025

import com.twelfthnightdj.advent2021.AocDays

class Y25D06 : AocDays() {
    override var dayId = 6

    override fun partA(): String {
        val factorsCount = input.size
        println(factorsCount)
        var operation = listOf<String>()
        val grid = mutableListOf<List<Long>>()
        input.forEachIndexed { idx, str ->
            if (idx == factorsCount - 1) {
                operation = str.split("\\s+".toRegex())
            } else {
                grid.add(str.split("\\s+".toRegex()).filter{ it.isNotEmpty() }.map { it.toLong() })
            }
        }
        var total = 0L
        for (col in 0 until grid[0].size) {
            val factors = mutableListOf<Long>()
            for (row in 0 until grid.size) {
                factors.add(grid[row][col])
            }
            total += if (operation[col] == "+") {
                factors.fold(0L) {acc , element -> acc + element}
            } else {
                factors.fold(1L) {acc, element -> acc * element}
            }
        }
        return total.toString()
    }
}