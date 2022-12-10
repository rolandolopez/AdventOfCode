package advent2022.day10

import com.twelfthnightdj.advent2021.AocDays
import kotlin.math.abs

class Y22D10 : AocDays() {
    override var dayId = 10
    var totalCycles = 0
    var register = 1
    var whenToCheck = listOf(20, 60, 100, 140, 180, 220)
    var signalStrength = 0
    var screen = MutableList(240) {'.'}

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
            signalStrength += (totalCycles * register)
        }
    }

    override fun partB(): String {
        input.forEach { line ->
            if (line == "noop") {
                checkForPixel()
                totalCycles++
            } else {
                checkForPixel()
                totalCycles++
                checkForPixel()
                totalCycles++
                register += line.split(" ")[1].toInt()
            }
        }
        screen.chunked(40).forEach {
            println(it.joinToString(""))
        }
        return super.partB()
    }

    private fun checkForPixel() {
        if(abs((totalCycles % 40) - register) <= 1) {
            screen[totalCycles] = '#'
        }
    }

    override fun reset() {
        totalCycles = 0
        register = 1
    }
}