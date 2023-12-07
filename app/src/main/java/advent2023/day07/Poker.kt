package advent2023.day07

class Poker (var hand: String, var wager: Int, var partB: Boolean = false): Comparable<Poker> {
    var map = mutableMapOf<Char, Int>()
    var strength: Int = 7
    init {
        for (c in hand) {
            map.putIfAbsent(c, 0)
            map[c] = map[c]!! + 1
        }
        if (partB) {
            val jokerCount = map['J']
            val mapB = map.filter{ it.key != 'J' }
            if (jokerCount != null && jokerCount != 5) {
                val highestCount = mapB.values.maxOrNull()
                val possibleClaimers = mutableListOf<Char>()
                mapB.forEach {
                    if (it.value == highestCount) {
                        possibleClaimers.add(it.key)
                    }
                }
                val claimer = (possibleClaimers.minByOrNull {
                    it.value()
                }!!)
                map[claimer] = map[claimer]!! + jokerCount
                map.remove('J')
            }
        }
        strength = handStrength()
    }
    fun handStrength(): Int {
        return if (partB) {
            handStrengthB()
        } else {
            handStrengthA()
        }
    }
    private fun handStrengthA(): Int {
        val vals = map.values.sortedDescending().toList()
        return when {
            vals[0] == 5 -> 1
            vals[0] == 4 -> 2
            vals[0] == 3 && vals[1] == 2 -> 3
            vals[0] == 3 && vals[1] == 1 -> 4
            vals[0] == 2 && vals[1] == 2 -> 5
            vals[0] == 2 && vals[1] == 1 -> 6
            else -> 7
        }
    }
    private fun handStrengthB(): Int {

        val vals = map.values.sortedDescending().toList()
        return when {
            vals[0] == 5 -> 1
            vals[0] == 4 -> 2
            vals[0] == 3 && vals[1] == 2 -> 3
            vals[0] == 3 && vals[1] == 1 -> 4
            vals[0] == 2 && vals[1] == 2 -> 5
            vals[0] == 2 && vals[1] == 1 -> 6
            else -> 7
        }
    }

    override fun compareTo(other: Poker): Int {
        if (this.strength != other.strength) {
            return this.strength compareTo other.strength
        }
        this.hand.forEachIndexed { index, c ->
            if (c.value() != other.hand[index].value()) {
                return c.value() compareTo other.hand[index].value()
            }
        }
        return 0
    }

    override fun toString(): String {
        return "Hand: $hand (${handStrength()}), Wager: $wager"
    }

    fun Char.value(): Int {
        return when (this) {
            'A' -> 1
            'K' -> 2
            'Q' -> 3
            'J' -> if (partB) 14 else 4
            'T' -> 5
            '9' -> 6
            '8' -> 7
            '7' -> 8
            '6' -> 9
            '5' -> 10
            '4' -> 11
            '3' -> 12
            '2' -> 13
            else -> 14

        }
    }
}