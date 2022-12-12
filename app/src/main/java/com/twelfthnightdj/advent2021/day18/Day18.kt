package com.twelfthnightdj.advent2021.day18

import com.twelfthnightdj.advent2021.AocDays

class Day18 : AocDays() {
    override var dayId = 18

    var currentIndex = -1
    var explodingPair: SnailfishNumber? = null

    override fun partA(): String {
        val numbers = mutableListOf<SnailfishNumber>()
        input.forEach {
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
        println("exploding pair: $explodingPair")
        explodingPair?.let { explode(it) }
    }

    private fun traverseToExplode(num: SnailfishNumber) {
        if (num.isSoloPair() && explodingPair == null && num.depth() >= 4) {
            println("solo pair $num with depth: ${num.depth(0)}")
            explodingPair = num
        }
        num.left?.let { traverseToExplode(it) }
        num.right?.let { traverseToExplode(it) }

    }

    private fun explode(num: SnailfishNumber) {
        println("try to explode $num")

//        findLeft(num.parent!!)?.let {
//            it.leftValue = it.leftValue!! + (num.leftValue ?: 0)
//        }
//        findRight(num.parent!!)?.let {
//            it.rightValue = it.rightValue!! + (num.rightValue ?: 0)
//        }
//        if (num.parent?.left == num) {
//            num.parent?.leftValue = 0
//        } else {
//            num.parent?.rightValue = 0
//        }
    }

    //    fun findLeft(num:SnailfishNumber): SnailfishNumber? {
//        if (num.parent == null) return null
//        if (num.parent!!.leftValue != null) return num.parent!!
//        return findLeft(num.parent!!)
//    }
    fun findLeft(num: SnailfishNumber): SnailfishNumber? {
        if (num.left == null) return num
        return findLeft(num.left!!)
    }
    fun findRight(num: SnailfishNumber): SnailfishNumber? {
        if (num.right == null) return num
        return findRight(num.right!!)
    }


//    fun findRight(num: SnailfishNumber): SnailfishNumber? {
//        println("findRight: $num")
//        if (num.parent == null) return null
////        if (num.parent!!.left != null) return findLeft(num.parent!!.left!!)
//        if (num.parent!!.rightValue != null) return num.parent!!
//        return findRight(num.parent!!)
//    }

    private fun process(ipt: String): SnailfishNumber {
        println("ipt: $ipt")
        currentIndex = 0
        return startNumber(ipt)
    }

    private fun startNumber(ipt: String, parent: SnailfishNumber? = null): SnailfishNumber {
        currentIndex++
        var isLeft = true
        val n = SnailfishNumber(parent)
        while (true) {
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
                    currentIndex++
                }
                ']' -> {
                    currentIndex++
                    return n
                }
                else -> {
                    if (isLeft) {
                        n.leftValue = ipt[currentIndex++].digitToInt()
                    } else {
                        n.rightValue = ipt[currentIndex++].digitToInt()
                    }
                }
            }
        }
    }
}
