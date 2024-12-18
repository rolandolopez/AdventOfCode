package advent2018.day01

import com.twelfthnightdj.advent2021.AocDays

class Y18D01 : AocDays() {
    override var dayId = 1
    private var addends = mutableListOf<Int>()

    override fun setup() {
        addends = input.map { it.toInt() }.toMutableList()
        super.setup()
    }

    override fun partA(): String {
        var total = 0
        addends.forEach {
            total += it
        }
        return total.toString()
    }

    override fun partB(): String {
        var total = 0
        val totals = mutableSetOf<Int>()
        totals.add(total)
        while(true) {
            addends.forEach {
                total += it
                if (totals.contains(total)) {
                    return total.toString()
                } else {
                    totals.add(total)
                }
            }
        }
    }
}