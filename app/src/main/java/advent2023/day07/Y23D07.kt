package advent2023.day07

import com.twelfthnightdj.advent2021.AocDays

class Y23D07 : AocDays() {
    override var dayId = 7
    var hands = mutableListOf<Poker>()

    override fun partA(): String {
        input.forEach { line ->
            val (cards, wager) = line.split(" ")
            hands.add(Poker(cards, wager.toInt()))
        }
        val winnings = hands.sortedDescending().mapIndexed { index, hand -> hand.wager * (index + 1) }.sum()
        return winnings.toString()
    }

    override fun reset() {
        hands.clear()
    }

    override fun partB(): String {
        input.forEach { line ->
            val (cards, wager) = line.split(" ")
            hands.add(Poker(cards, wager.toInt(), true))
        }
        val winnings = hands.sortedDescending().mapIndexed { index, hand -> hand.wager * (index + 1) }.sum()
        return winnings.toString()
    }
}