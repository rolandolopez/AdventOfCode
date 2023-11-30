package advent2015.day05

import com.twelfthnightdj.advent2021.AocDays

class Y15D05 : AocDays() {
    override var dayId = 5
    private val vowels = listOf('a','e','i','o','u')
    var total = 0
    var doubled = "(.)\\1".toRegex()
    var third = "ab|cd|pq|xy".toRegex()
    var b1 = "(.)(.).*\\1\\2".toRegex()
    var b2 = "(.).\\1".toRegex()
    override fun partA(): String {
        input.forEach { line ->
            if (vowelCheck(line) && doubleCheck(line) && thirdRequirement(line)) {
                total++
            }

        }
        return total.toString()
    }

    private fun vowelCheck(line: String) = line.count { vowels.contains(it) } >= 3
    private fun doubleCheck(line: String) = doubled.find(line)?.let { true } ?: false
    private fun thirdRequirement(line: String) = third.find(line) == null


    override fun reset() {
        total = 0
    }

    override fun partB(): String {
        input.forEach { line ->
            if (b1Check(line) && b2Check(line)) {
                total++
            }
        }
        return total.toString()
    }

    private fun b1Check(line: String) = b1.find(line)?.let { true } ?: false
    private fun b2Check(line: String) = b2.find(line)?.let { true } ?: false
}