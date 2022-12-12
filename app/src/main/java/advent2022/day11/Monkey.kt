package advent2022.day11

class Monkey(private val zoo: List<Monkey>) {
    private var items = mutableListOf<Long>()
    lateinit var operation: (Long) -> Long
    var operand = 0L
    var testDivisor = 1L
    var ifTrue = 0
    var ifFalse = 0
    var monkeyNum = zoo.size
    var itemsInpected = 0L

    fun catchItem(item: Long) {
        items.add(item)
    }

    fun inspectItems(changeToWorryLevel: (Long) -> Long) {
        itemsInpected += items.size
        items.forEach { item ->
            val worry = changeToWorryLevel(operation(item))

            if (worry % testDivisor == 0L) {
                zoo[ifTrue].catchItem(worry)
            } else {
                zoo[ifFalse].catchItem(worry)
            }
        }
        items.clear()
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