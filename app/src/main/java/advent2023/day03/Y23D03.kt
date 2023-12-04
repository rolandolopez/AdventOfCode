package advent2023.day03

import com.twelfthnightdj.advent2021.AocDays
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.Observable

class Y23D03 : AocDays() {
    override var dayId = 3
    var grid: MutableSet<String> = mutableSetOf()
    var sum = 0

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
                        println("sum: $sum")
                    }
                    parts = parts.next()
                }
            }
            println("final: $sum")
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
        return super.partB()
    }

    override fun reset() {
        super.reset()
    }
}