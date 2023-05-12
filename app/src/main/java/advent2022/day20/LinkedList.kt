package advent2022.day20

import kotlin.math.abs

class LinkedList {
    var head: LlNode? = null
    var size = 0
    var end: LlNode? = null
    var zero: LlNode? = null
    private val moveOrder = mutableListOf<LlNode>()

    fun moveAll() {
        recordOrder()
        moveOrder.forEach { curr ->
            val moveAmount = abs(curr.key) % (size - 1)
            if (curr.key != 0) {
                var h = curr.next
                if (head == curr) head = curr.next
                curr.prev?.next = curr.next
                curr.next?.prev = curr.prev
                curr.next = null
                curr.prev = null
                if (curr.key > 0) {
                    repeat(moveAmount - 1) {
                        h = h?.next ?: head
                    }
                } else {
                    repeat(moveAmount + 1) {
                        h = h?.prev ?: end
                    }
                }
                h?.next?.prev = curr
                curr.next = h?.next
                h?.next = curr
                curr.prev = h
            } else zero = curr
        }
    }

    fun nth(n: Int): Int {
        var current = zero
        repeat(n % size) {
            current = current?.next ?: head
        }
        val answer = current?.key ?: 0
        println("the $n th term is $answer")
        return answer

    }

    fun add(new: Int) {
        val newNode = LlNode(new)
        if (new == 0) zero = newNode
        if (size == 0) {
            head = newNode
            end = newNode
            size++
        } else {
            end?.let {
                it.next = newNode
                newNode.prev = end
                end = newNode
                size++
            }
        }
    }

    private fun recordOrder() {
        var now = head
        repeat(size) {
            now?.let {
                moveOrder.add(it)
                now = now?.next
            }
        }
    }

    override fun toString(): String {
        var now = head
        val l = mutableListOf<Int>()
        repeat(size) {
            now?.let { l.add(it.key) }
            now = now?.next
        }
        println("0 index: ${l.indexOf(0)}")
        return l.toString()
    }
}