package advent2025

import com.twelfthnightdj.advent2021.AocDays

class Y25D03 : AocDays() {
    override var dayId = 3

    override fun partA(): String {
        var total = 0L
        input.forEach { bank ->
            val each = bank.map { it.toString().toInt() }
            var runner = ""
            var startingIndex = 0
            for (batteryCount in 2 downTo 1) {
                val maxIndex = getMaxIndex(each, startingIndex, each.size - batteryCount)
                runner += each[maxIndex].toString()
                startingIndex = maxIndex + 1
            }
            total += runner.toLong()
        }
        return total.toString()
    }

    override fun partB(): String {
        var total = 0L
        input.forEach { bank ->
            val each = bank.map { it.toString().toInt() }
            var runner = ""
            var startingIndex = 0
            for (batteryCount in 12 downTo 1) {
                val maxIndex = getMaxIndex(each, startingIndex, each.size - batteryCount)
                runner += each[maxIndex].toString()
                startingIndex = maxIndex + 1
            }
            total += runner.toLong()
        }
        return total.toString()
    }

    private fun getMaxIndex(numbers: List<Int>, start: Int = 0, end: Int = numbers.size - 1): Int {
        var maxIndex = start
        var maxValue = numbers[maxIndex]
        for (i in start..end) {
            if (numbers[i] > maxValue) {
                maxIndex = i
                maxValue = numbers[i]
            }
        }
        return maxIndex
    }
}