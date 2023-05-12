package advent2022.day20

import com.twelfthnightdj.advent2021.AocDays

class Y22D20 : AocDays() {
    var nodes = LinkedList()
    override var dayId = 20
    override fun setup() {
        input.forEach {
            nodes.add(it.toInt())
        }
        println("Start: ${nodes}")
    }

    override fun partA(): String {
        nodes.moveAll()
        println("at end: ${nodes}")
        println("zero node: ${nodes.zero?.next}")
        println("head: ${nodes.head}")
        val answer = nodes.nth(1000) + nodes.nth(2000) + nodes.nth(3000)
        return answer.toString()
    }

    override fun partB(): String {
        return super.partB()
    }

    override fun reset() {
        super.reset()
    }
}