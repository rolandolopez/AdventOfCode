package advent2018.day09

import advent2022.day20.LlNode

class Marbles() {
    var currentMarbleScore = 0
    var currentMarble: LlNode = LlNode(0)
    var zeroNode: LlNode = currentMarble
    init {
        val m1 = LlNode(1)
        val m2 = LlNode(2)
        currentMarble.next = m2
        currentMarble.prev = m1
        m1.next = currentMarble
        m1.prev = m2
        m2.next = m1
        m2.prev = currentMarble
        currentMarble = m2
        currentMarbleScore = 2
    }

    fun insertAfterCurrentMarble(marble: Int) {
        val m = LlNode(marble)
        val next = currentMarble.next
        next?.prev = m
        m.next = next
        currentMarble.next = m
        m.prev = currentMarble
        currentMarble = m
    }

    fun removeCurrentMarble(): Int {
        val toReturn = currentMarble.key
        val prev = currentMarble.prev
        val next = currentMarble.next
        prev?.next = next
        next?.prev = prev
        currentMarble = next!!
        return toReturn
    }

    fun moveClockwise(n: Int = 1) {
        repeat(n) {
            currentMarble = currentMarble.next!!
        }
    }

    fun moveCounterClockwise(n: Int = 1) {
        repeat(n) {
            currentMarble = currentMarble.prev!!
        }
    }

    override fun toString(): String {
        println("(${zeroNode.key})")
        var printNode = zeroNode
        do {
            printNode = printNode.next!!
            println(" -(${printNode.key})- ")
        } while(printNode.key != 0)
        return ""
    }
}