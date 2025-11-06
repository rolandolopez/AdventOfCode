package advent2018.day05

import com.twelfthnightdj.advent2021.AocDays
import kotlin.math.abs
import kotlin.math.max

class Y18D05 : AocDays() {
    override var dayId = 5

    override fun partA(): String {
        val chars = inputAsString.toCharArray().map { it.code }.toMutableList()
        var index = 0
        while (true) {
            index = max(0, index)
            if (index + 1 >= chars.size) {
                break
            }
            if (abs(chars[index] - chars[index + 1]) == 32) {
                chars.removeAt(index + 1)
                chars.removeAt(index)
                index -= 2
            } else {
                index++
            }
        }
        return chars.size.toString()
    }
}