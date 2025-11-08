package advent2018.day08

import com.twelfthnightdj.advent2021.AocDays

class Y18D08 : AocDays() {
    override var dayId = 8

    var metaDataSum = 0
    var currentIndex = 0
    var nums: List<Int> = listOf()

    override fun partA(): String {
        nums = inputAsString.split(" ").map { it.toInt() }
        while (currentIndex < nums.size) {
            createNode()
        }
        return metaDataSum.toString()
    }

    private fun createNode() {
        val numOfChildNodes = nums[currentIndex++]
        val numOfMetaDataEntries = nums[currentIndex++]
        repeat(numOfChildNodes) {
            createNode()
        }
        repeat(numOfMetaDataEntries) {
            metaDataSum += nums[currentIndex++]
        }
    }
}