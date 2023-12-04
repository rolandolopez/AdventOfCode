package advent2023.day03

import com.twelfthnightdj.advent2021.AocDays
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class Y23D03 : AocDays() {
    override var dayId = 3
    var grid: MutableSet<String> = mutableSetOf()
    var gridB: MutableMap<String, Int> = mutableMapOf()
    var sum = 0
    var sumB = 0

    override fun partA(): String {
        input.forEachIndexed { index, line ->
            var all = "[^0-9.]".toRegex().find(line)
            while (all != null) {
                grid.add("$index#${all.range.first}")
                all = all.next()
            }
        }

        GlobalScope.launch {
            check()
        }

        return sum.toString()
    }

    private suspend fun check() {
        coroutineScope {
            input.forEachIndexed { index, line ->
                var parts = "\\d+".toRegex().find(line)
                while (parts != null) {
                    if (isPartNumber(index, parts.range)) {
                        sum += parts.value.toInt()
                    }
                    parts = parts.next()
                }
            }
            println("part A: $sum")
        }
    }

    private fun isPartNumber(x: Int, range: IntRange): Boolean {
        ((range.first - 1)..(range.last +1)).forEach {
            if (grid.contains("${x - 1}#$it")) {
                return true
            }
            if (grid.contains("${x + 1}#$it")) {
                return true
            }
        }
        if (grid.contains("$x#${range.first - 1}") || (grid.contains("$x#${range.last + 1}"))) {
            return true
        }
        return false
    }

    override fun partB(): String {
        input.forEachIndexed { index, line ->
            var all = "\\*".toRegex().find(line)
            while (all != null) {
                gridB["$index#${all.range.first}"] = 0
                all = all.next()
            }
        }

        GlobalScope.launch {
            checkB()
        }
        return sum.toString()
    }

    private fun gearCalculation(x: Int, range: IntRange, v: Int): Int {
        var getOut = 0
        ((range.first - 1)..(range.last + 1)).forEach {
             gridB.computeIfPresent("${x - 1}#$it") { _, value ->
                 if (value != 0) {
                     getOut = value * v
                     value * v
                 } else {
                     v
                 }
             }
            if (getOut > 0) return getOut
            gridB.computeIfPresent("${x + 1}#$it") { _, value ->
                 if (value != 0) {
                     getOut = value * v
                     value * v
                 } else {
                     v
                 }
             }
            if (getOut > 0) return getOut
        }
        gridB.computeIfPresent("$x#${range.first - 1}") { _ , value ->
            if (value != 0) {
                getOut = value * v
                value * v
            } else {
                v
            }
        }
        if (getOut > 0) return getOut

        gridB.computeIfPresent("$x#${range.last + 1}") { _ , value ->
            if (value != 0) {
                getOut = value * v
                value * v
            } else {
                v
            }
        }
        if (getOut > 0) return getOut

        return 0
    }

    private suspend fun checkB() {
        coroutineScope {
            input.forEachIndexed { index, line ->
                var parts = "\\d+".toRegex().find(line)
                while (parts != null) {
                    val t = gearCalculation(index, parts.range, parts.value.toInt())
                    sumB += t
                    parts = parts.next()
                }
            }
            println("partB: $sumB")
        }
    }

    override fun reset() {
        sum = 0
        super.reset()
    }
}