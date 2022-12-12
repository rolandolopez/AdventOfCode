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
                    line.removePrefix("  Starting items: ").split(", ").map { it.toInt() }.forEach {
                        currentMonkey.catchItem(it)
                    }
                }
                line.startsWith("  Operation: ") -> {
                    line.removePrefix("  Operation: new = old ").split(" ").forEach {
                        when (it) {
                            "old" -> currentMonkey.operation = Operation.SQUARE
                            "+" -> currentMonkey.operation = Operation.ADD
                            "*" -> currentMonkey.operation = Operation.MULTIPLY
                            else -> currentMonkey.operand = it.toInt()
                        }
                    }
                }
                line.startsWith("  Test: divisible by ") -> {
                    currentMonkey.testDivisor = line.removePrefix("  Test: divisible by ").toInt()
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
                it.inspectItems()
            }
        }
        println("afterward")
        zoo.forEach {
            println(it)
        }
        var product = 1
        zoo.sortedByDescending { it.itemsInpected }.take(2).forEach {
            product *= it.itemsInpected
        }
        return product.toString()
    }

    override fun partB(): String {
        return super.partB()
    }

    override fun reset() {
        super.reset()
    }
}

