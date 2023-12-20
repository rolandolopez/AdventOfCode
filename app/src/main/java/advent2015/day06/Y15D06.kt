package advent2015.day06

import com.twelfthnightdj.advent2021.AocDays
import java.lang.Integer.max
import java.lang.Integer.min

class Y15D06 : AocDays() {
    override var dayId = 6
    val grid = mutableMapOf<String, Boolean>()
    val gridB = mutableMapOf<String, Int>()

    override fun partA(): String {
        input.forEach { line ->
            val regex = "(turn off |turn on |toggle )([0-9]+),([0-9]+) through ([0-9]+),([0-9]+)".toRegex()
            val matches = regex.find(line)!!.groupValues
            (matches[3].toInt()..matches[5].toInt()).forEach { y ->
                (matches[2].toInt()..matches[4].toInt()).forEach { x ->
                    val bulb = grid.getOrPut("$x#$y") { false }
                    when (matches[1]) {
                        "turn on " -> grid["$x#$y"] = true
                        "turn off " -> grid["$x#$y"] = false
                        "toggle " -> grid["$x#$y"] = !bulb
                    }
                }
            }
        }
        return grid.values.count { it }.toString()
    }

    override fun partB(): String {
        input.forEach { line ->
            val regex = "(turn off |turn on |toggle )([0-9]+),([0-9]+) through ([0-9]+),([0-9]+)".toRegex()
            val matches = regex.find(line)!!.groupValues
            (matches[3].toInt()..matches[5].toInt()).forEach { y ->
                (matches[2].toInt()..matches[4].toInt()).forEach { x ->
                    gridB.getOrPut("$x#$y") { 0 }
                    when (matches[1]) {
                        "turn on " -> gridB.computeIfPresent("$x#$y") { _, value ->
                            value + 1
                        }
                        "turn off " -> gridB.computeIfPresent("$x#$y") { _, value ->
                            max(value - 1, 0)
                        }
                        "toggle " -> gridB.computeIfPresent("$x#$y") { _, value ->
                            value + 2
                        }
                    }
                }
            }
        }
        return gridB.values.sumOf { it }.toString()
    }
}