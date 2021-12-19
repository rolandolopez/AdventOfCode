package com.twelfthnightdj.advent2021.day18

import com.twelfthnightdj.advent2021.AocDays

class Day18 : AocDays() {
    override var dayId = 18

    var currentIndex = -1
    var explodingPair: SnailfishNumber? = null

    override fun partA(): String {
        val numbers = mutableListOf<SnailfishNumber>()
        trialInput.forEach {
            numbers.add(process(it))
        }

        reduce(numbers[0])

        numbers.forEach {
            println("new: ${it}")
        }

        return ""
    }

    private fun reduce(num: SnailfishNumber) {
        traverseToExplode(num)
        explodingPair?.let { explode(it) }
    }

    private fun traverseToExplode(num: SnailfishNumber) {
        println("checking: $num")
        if (num.isSoloPair() && explodingPair == null) {
            println("solo pair $num with depth: ${num.depth(0)}")
            explodingPair = num
        }
        println("left: ${num.left}")
        num.left?.let { traverseToExplode(it) }
        println("right: ${num.right}")
        num.right?.let {
            println("checking RIGHT: $it")
            traverseToExplode(it)
        }

    }

    private fun explode(num: SnailfishNumber) {
        println("try to explode $num")
        findLeft(num)?.let {
            println("Left number: $it")
            it.value = it.value!! + (num.left!!.value ?:0)
        }
        findRight(num)?.let {
            println("Right number: $it")
            it.value = it.value!! + (num.right!!.value ?: 0)
        }
        if (num.parent?.left == num) {
            num.parent?.left?.value = 0
        } else {
            num.parent?.right?.value = 0
        }
    }
    fun findLeft(num:SnailfishNumber): SnailfishNumber? {
        if (num.parent == null) return null
        if (num.parent!!.left?.value != null) return num.parent!!.left
        return findLeft(num.parent!!)
    }

    fun findRight(num:SnailfishNumber): SnailfishNumber? {
        if (num.parent == null) return null
        if (num.parent!!.right?.value != null) return num.parent!!.right
        return findLeft(num.parent!!)
    }

    private fun process(ipt: String): SnailfishNumber {
        println("ipt: $ipt")
        currentIndex = -1
        return startNumber(ipt)
    }
    private fun startNumber(ipt: String, parent: SnailfishNumber? = null): SnailfishNumber {
        currentIndex++
        var isLeft = true
        var n = SnailfishNumber(parent)
        while(true) {
            when (ipt[currentIndex]) {
                '[' -> {
                    if (isLeft) {
                        n.left = startNumber(ipt, n)
                    } else {
                        n.right = startNumber(ipt, n)
                    }
                }
                ',' -> {
                    isLeft = false
                    n.right = startNumber(ipt, n)
                }
                ']' -> {
                    currentIndex++
                    return n
                }
                else -> {
                    n.value = ipt[currentIndex++].digitToInt()
                    return n
                }
            }
        }
    }
}
