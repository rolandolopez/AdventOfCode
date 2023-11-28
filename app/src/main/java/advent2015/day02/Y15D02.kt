package advent2015.day02

import com.twelfthnightdj.advent2021.AocDays
import java.lang.Integer.min

class Y15D02 : AocDays() {
    override var dayId = 2

    private fun cover(l: Int, w: Int, h: Int): Int {
        val side1 = l * w
        val side2 = l * h
        val side3 = w * h
        return (2 * (side1 + side2 + side3)) + min(min(side1, side2), side3)
    }

    override fun partA(): String {
        var total = 0
        input.forEach { inp ->
            val (l, w, h)  = inp.split("x").map { it.toInt() }
            total += cover (l, w, h)
        }
        return total.toString()
    }

    override fun partB(): String {
        var total = 0
        input.forEach { inp ->
            val cubed = inp.split("x").map { it.toInt() }
            total += (inp.split("x").map { it.toInt() }.sorted().take(2).sum() * 2) + (cubed[0] * cubed[1] * cubed[2])

        }
        return total.toString()
    }
}