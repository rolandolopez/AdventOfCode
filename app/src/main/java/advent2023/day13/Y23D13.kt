package advent2023.day13

import com.twelfthnightdj.advent2021.AocDays
import java.lang.Integer.min

class Y23D13 : AocDays() {
    override var dayId = 13

    var grids = mutableListOf<MutableList<String>>()
    var grid = mutableListOf<String>()
    var sum = 0L

    override fun partA(): String {
        input.forEach { line ->
            when {
                line.isEmpty() -> {
                    grids.add(grid.toMutableList())
                    grid.clear()
                }

                else -> {
                    grid.add(line)
                }
            }
        }
        grids.add(grid.toMutableList())
        grid.clear()
        grids.forEach { g ->
            val vert = checkGridVertically(g)
            val horiz = checkGridHorizontally(g)
            println("vert: $vert, horiz: $horiz")
            sum += vert + (horiz * 100)
        }
        return sum.toString()
    }

    private fun checkGridVertically(g: MutableList<String>): Int {
        val possibles = mutableSetOf<Int>()
        val firstLine = g.first()
        (1 until firstLine.length).forEach {
            val left = firstLine.substring(0, it).reversed()
            val right = firstLine.substring(it)
            val minim = min(left.length, right.length)
            if (left.take(minim) == right.take(minim)) {
                possibles.add(it)
            }
        }
        g.drop(1).forEach { line ->
            possibles.toMutableList().forEach {
                val left = line.substring(0, it).reversed()
                val right = line.substring(it)
                val minim = min(left.length, right.length)
                if (left.take(minim) != right.take(minim)) {
                    possibles.remove(it)
                    if (possibles.isEmpty()) return 0
                }
            }
        }
        return if (possibles.isEmpty()) 0 else possibles.first()
    }

    private fun checkGridHorizontally(g: MutableList<String>): Int {
        (1 until g.size).forEach {
            val top = g.subList(0, it).reversed()
            val bottom = g.subList(it, g.size)

            val half = min(top.size, bottom.size)
            if (top.take(half) == bottom.take(half)) {
                println("it's a match at $it")
                return it
            }
        }
        return 0
    }

    override fun partB(): String {
        return super.partB()
    }
}