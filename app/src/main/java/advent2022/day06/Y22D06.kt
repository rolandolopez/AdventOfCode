package advent2022.day06

import com.twelfthnightdj.advent2021.AocDays

class Y22D06 : AocDays() {
    override var dayId = 6

    override fun setup() {
        super.setup()
    }

    override fun partA(): String {
        return "${ checkIt(inputAsString, 4) }"
    }

    private fun checkIt(stringToCheck: String, len: Int): Int {
        var index = 0
        while(true) {
            val check = stringToCheck.substring(index, (index + len))
            if (check.toSet().size == len) return (index + len)
            index++
            if (index > (stringToCheck.length - len)) break
        }
        return -1
    }

    override fun partB(): String {
        return super.partB()
    }

    override fun reset() {
        super.reset()
    }
}