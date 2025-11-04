package advent2018.day02

import com.twelfthnightdj.advent2021.AocDays

class Y18D02 : AocDays() {
    override var dayId = 2
    override fun partA(): String {

        var tripleCounter = 0
        var doubleCounter = 0

        input.forEach big@ { str ->
            println("****************")
            println("str: $str")
            val massaged = str.toCharArray().sorted().joinToString("")
            println("mas: $massaged")
            if (hasExactlyThreeofSameChar(massaged)) {
                println("adding $str to the triple counter")
                tripleCounter++
            }
            if (hasExactlyTwoofSameChar(massaged)) {
                println("adding $str to the double counter")
                doubleCounter++
            }
        }

        val total = doubleCounter * tripleCounter
        return total.toString()
    }

    fun hasExactlyThreeofSameChar(s: String): Boolean {
        // Group the characters and count the occurrences of each
        val frequencies = s.groupingBy { it }.eachCount()

        // Check if any character has a count of exactly 3
        return frequencies.containsValue(3)
    }

    fun hasExactlyTwoofSameChar(s: String): Boolean {
        // Group the characters and count the occurrences of each
        val frequencies = s.groupingBy { it }.eachCount()

        // Check if any character has a count of exactly 2
        return frequencies.containsValue(2)
    }
}