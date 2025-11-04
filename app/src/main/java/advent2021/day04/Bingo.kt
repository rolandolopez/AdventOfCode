package advent2021.day04

class Bingo (seed: List<String>) {
    private var callout = prepareCallouts(seed[0])
    var cards = mutableListOf<advent2021.day04.Card>()
    init {
        prepareCards(seed)
    }

    fun startAGame(): Long {
        var position = 0
        while (true) {
            val calling = callout[position]
            cards.forEach cardLoop@ { card ->
                card.numberCalled(calling)?.let {
                    return it
                }
            }
            position++
        }
    }

    fun startBGame(): Long {
        val winningBoards = mutableSetOf<Int>()
        var position = 0
        while (true) {
            val calling = callout[position]
            cards.forEachIndexed cardLoop@ { index, card ->
                card.numberCalled(calling)?.let {
                    winningBoards.add(index)
                    if (winningBoards.size == cards.size) {
                        return it
                    }
                }
            }
            position++
        }
    }

    private fun prepareCallouts(callouts: String): List<Int> = callouts.split(",").map { it.toInt() }

    private fun prepareCards(seed: List<String>) {
        val cardGrid = mutableListOf<Int>()
        seed.forEachIndexed boardLoop@ { index, line ->
            if (index < 2) return@boardLoop
            if (line.isEmpty()) {
                cards.add(Card(cardGrid))
                cardGrid.clear()
                return@boardLoop
            }
            cardGrid.addAll(line.split("\\s+".toRegex()).filter { it.isNotEmpty()}.map { it.toInt() })
        }
        cards.add(Card(cardGrid))
    }
}
