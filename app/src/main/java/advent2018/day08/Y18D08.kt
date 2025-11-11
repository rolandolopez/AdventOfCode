package advent2018.day08

import com.twelfthnightdj.advent2021.AocDays

class Y18D08 : AocDays() {
    override var dayId = 8

    var metaDataSum = 0
    var currentIndex = 0
    var nums: List<Int> = listOf()
    override fun setup() {
        nums = inputAsString.split(" ").map { it.toInt() }
    }

    override fun partA(): String {
        while (currentIndex < nums.size) {
            createNodeA()
        }
        return metaDataSum.toString()
    }

    override fun reset() {
        metaDataSum = 0
        currentIndex = 0
    }

    override fun partB(): String {
        var bSum = 0
        while (currentIndex < nums.size) {
             bSum += createNodeB()
        }
        return bSum.toString()
    }

    private fun createNodeA() {
        val numOfChildNodes = nums[currentIndex++]
        val numOfMetaDataEntries = nums[currentIndex++]
        repeat(numOfChildNodes) {
            createNodeA()
        }
        repeat(numOfMetaDataEntries) {
            metaDataSum += nums[currentIndex++]
        }
    }

    private fun createNodeB(): Int {
        val numOfChildNodes = nums[currentIndex++]
        val numOfMetaDataEntries = nums[currentIndex++]
        var sum = 0
        val childNodes: MutableList<Int> = mutableListOf()
        repeat(numOfChildNodes) {
            childNodes.add(createNodeB())
        }
        if (numOfChildNodes == 0) {
            repeat(numOfMetaDataEntries) {
                sum += nums[currentIndex++]
            }
            println("returning zero child: $sum")
            return sum
        } else {
            val indexes: MutableList<Int> = mutableListOf()
            repeat(numOfMetaDataEntries) {
                indexes.add(nums[currentIndex++])
            }
            indexes.forEach { idx ->
                println("*******  $idx < ${childNodes.size - 1}  sum: $sum")
                if (idx < childNodes.size && idx > 0) {
                    println("adding: ${childNodes[idx - 1]}")
                    sum += childNodes[idx - 1]
                }
            }
            println("returning the else of childnodes${childNodes} of index: $indexes: $sum")
            return sum
        }

    }
}