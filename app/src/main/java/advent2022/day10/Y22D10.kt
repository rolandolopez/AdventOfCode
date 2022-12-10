package advent2022.day10

import com.twelfthnightdj.advent2021.AocDays

class Y22D10 : AocDays() {
    override var dayId = 10
    var totalCycles = 0
    var register = 1
    var whenToCheck = listOf(20, 60, 100, 140, 180, 220)
    var signalStrength = 0

    override fun setup() {

    }

    override fun partA(): String {
        input.forEach { line ->
            if (line == "noop") {
                totalCycles++
                checkCycles()
            } else {
                totalCycles++
                checkCycles()
                totalCycles++
                checkCycles()
                register += line.split(" ")[1].toInt()
            }

        }
        return signalStrength.toString()
    }

    private fun checkCycles() {
        if (whenToCheck.contains(totalCycles)) {
            println("adding $totalCycles * $register")
            signalStrength += (totalCycles * register)
        }
    }

    override fun partB(): String {
        return super.partB()
    }

    override fun reset() {
        super.reset()
    }
}