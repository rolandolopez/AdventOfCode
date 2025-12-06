package advent2025

import com.twelfthnightdj.advent2021.AocDays

class Y25D06 : AocDays() {
    override var dayId = 6

    override fun partA(): String {
        val factorsCount = input.size
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

    override fun partB(): String {
        var operation = ' '
        val width = input[0].length + 2
        val height = input.size

        var total = 0L
        val factors = mutableListOf<Long>()
        val newInput = input.toMutableList()
        var starter = input[0]
        starter = starter.prependIndent("  ")
        newInput[0] = starter
        for (w in 0 until width) {
            var factor = ""
            for (h in 0 until height) {
                if (w < newInput[h].length) {
                    if (h == height - 1) {
                        if (newInput[h][w] != ' ') {
                            operation = newInput[h][w]
                        }
                    } else {
                        if (newInput[h][w] != ' ') {
                            factor += newInput[h][w]
                        }
                    }
                }
            }
            if (factor == "") {
                total += if (operation == '+') {
                    factors.fold(0L) {acc, element -> acc + element}
                } else {
                    factors.fold(1L) {acc, element -> acc * element}
                }
                factors.clear()
            } else {
                factors.add(factor.toLong())
            }
        }
        total += if (operation == '+') {
            factors.fold(0L) {acc, element -> acc + element}
        } else {
            factors.fold(1L) {acc, element -> acc * element}
        }

        return total.toString()
    }
}