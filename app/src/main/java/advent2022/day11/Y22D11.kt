package advent2022.day11

import com.twelfthnightdj.advent2021.AocDays

class Y22D11 : AocDays() {
    override var dayId = 11
    lateinit var currentMonkey: Monkey
    var zoo = mutableListOf<Monkey>()
    override fun setup() {
        input.forEach { line ->
            when {
                line.startsWith("Monkey ") -> {
                    currentMonkey = Monkey(zoo)
                }
                line.startsWith("  Starting items: ") -> {
                    line.removePrefix("  Starting items: ").split(", ").map { it.toLong() }.forEach {
                        currentMonkey.catchItem(it)
                    }
                }
                line.startsWith("  Operation: ") -> {
                    val op = line.substringAfterLast(" ")
                    currentMonkey.operation = when {
                        line.contains("+") -> ({it + op.toLong()})
                        op == "old" -> ({it * it})
                        else -> ({it * op.toLong()})
                    }
                }
                line.startsWith("  Test: divisible by ") -> {
                    currentMonkey.testDivisor = line.removePrefix("  Test: divisible by ").toLong()
                }
                line.startsWith("    If true: throw to monkey ") ->
                    currentMonkey.ifTrue = line.removePrefix("    If true: throw to monkey ").toInt()
                line.startsWith("    If false: throw to monkey ") ->
                    currentMonkey.ifFalse = line.removePrefix("    If false: throw to monkey ").toInt()
                line.isEmpty() -> {
                    zoo.add(currentMonkey)

                }
            }
        }
        zoo.add(currentMonkey)
    }

    override fun partA(): String {
        repeat(20) {
            zoo.forEach {
                it.inspectItems { it1 -> it1 / 3 }
            }
        }
        return getAnswer().toString()
    }

    private fun getAnswer(): Long {
        var product = 1L
        zoo.sortedByDescending { it.itemsInpected }.take(2).forEach {
            product *= it.itemsInpected
        }
        return product
    }

    override fun partB(): String {
        val testProduct = zoo.map { it.testDivisor }.reduce(Long::times)
        repeat(10000) {
            zoo.forEach {
                it.inspectItems { it1 -> it1 % testProduct }
            }
        }
        return getAnswer().toString()
    }

    override fun reset() {
        zoo.clear()
        setup()
    }
}

