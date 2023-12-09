package advent2023.day09

import com.twelfthnightdj.advent2021.AocDays

class Y23D09 : AocDays() {
    override var dayId = 9
    var sum = 0L
    override fun partA(): String {
        input.forEach { line ->
            var inter = line.split(" ").map { it.toLong() }
            sum += inter.last()
            do {
                inter = process(inter).toMutableList()
                sum += inter.last()
            } while (inter.any{it != 0L})
        }
        return sum.toString()
    }

    private fun process(original: List<Long>): List<Long> {
        val inter = mutableListOf<Long>()
        original.windowed(2).forEach {
            inter.add(it[1] - it[0])
        }
        return inter
    }

    override fun reset() {
        sum = 0L
    }

    override fun partB(): String {
        input.forEach { line ->
            val inter = line.split(" ").map { it.toLong() }
            sum += inter.first() - iterate(inter)
        }
        return sum.toString()
    }

    private fun iterate(list: List<Long>): Long {
        val l = process(list)
        if (l.all { it == 0L }) {
            return 0L
        }

        return l.first() - iterate(l)
    }
}