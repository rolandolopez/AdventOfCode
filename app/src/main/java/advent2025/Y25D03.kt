package advent2025

import com.twelfthnightdj.advent2021.AocDays

class Y25D03 : AocDays() {
    override var dayId = 3

    override fun partA(): String {
        var total = 0
        input.forEach { bank ->
            val each = bank.map { it.toString().toInt() }
            val tensIndex = getMaxIndex(each, 0, each.size - 2)
            val onesIndex = getMaxIndex(each,tensIndex + 1)
            val largest = (each[tensIndex] * 10) + each[onesIndex]
            total += largest
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