package advent2021.day21

class DiracPlayer (val id: Int, val startingSpace: Int) {
    var score = 0L
    private var currentSpace = startingSpace

    fun move(spaces: Int) {
        currentSpace = ((currentSpace + spaces) % 10)
        if (currentSpace == 0) currentSpace = 10
        score += currentSpace
    }

    override fun toString(): String {
        return "Player ${(id + 1)} on space $currentSpace has a score of $score"
    }
}
