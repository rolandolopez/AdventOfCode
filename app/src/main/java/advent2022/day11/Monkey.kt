package advent2022.day11

class Monkey(private val zoo: List<Monkey>) {
    private var items = mutableListOf<Int>()
    var operation: Operation = Operation.ADD
    var operand = 0
    var testDivisor = 1
    var ifTrue = 0
    var ifFalse = 0
    var monkeyNum = zoo.size
    var itemsInpected = 0

    fun catchItem(item: Int) {
        items.add(item)
    }

    fun inspectItems() {
        itemsInpected += items.size
        while (items.isNotEmpty()) {
            var item = items.removeFirst()
            item = when (operation) {
                Operation.ADD -> item + operand
                Operation.MULTIPLY -> item * operand
                Operation.SQUARE -> item * item
            }
            item /= 3

            if (item % testDivisor == 0) {
                zoo[ifTrue].catchItem(item)
            } else {
                zoo[ifFalse].catchItem(item)
            }
        }
    }

    override fun toString(): String {
        println("""
            Monkey $monkeyNum
              Starting items: $items
              Operation: new = old $operation $operand
              Test: divisible by $testDivisor
                If true: throw to monkey $ifTrue
                If false: throw to monkey $ifFalse
                
        """.trimIndent())
        return ""
    }
}

enum class Operation {ADD, MULTIPLY, SQUARE}