package advent2024.day05

import com.twelfthnightdj.advent2021.AocDays

class Y24D05 : AocDays() {
    override var dayId = 5
    var rules = mutableListOf<Pair<Int, Int>>()
    var updates = mutableListOf<List<Int>>()

    override fun setup() {
        var inRulesSection = true
        input.forEach { line ->
            if (line.isEmpty()) {
                inRulesSection = false
                return@forEach
            }
            if (inRulesSection) {
                val (f, s) = line.split("|").map { it.toInt() }
                rules.add(Pair(f,s))
            } else {
                updates.add(line.split(",").map { it.toInt() })
            }
        }
        super.setup()
    }

    override fun partA(): String {
        var total = 0
        updates.forEach updateForEach@{ update ->
            rules.forEach ruleForEach@{ rule ->
                if (update.containsAll(rule.toList())) {
                    if (update.indexOf(rule.first) > update.indexOf(rule.second)) {
                        return@updateForEach
                    }
                }
            }
            total += update[update.size/2]
        }
        return total.toString()
    }

    override fun partB(): String {
        var total = 0
        val wrongUpdates = mutableMapOf<Int, List<Int>>()
        updates.forEachIndexed updateForEach@{ index, update ->
            rules.forEach ruleForEach@{ rule ->
                if (update.containsAll(rule.toList())) {
                    if (update.indexOf(rule.first) > update.indexOf(rule.second)) {
                        wrongUpdates[index] = update
                        return@updateForEach
                    }
                }
            }
        }
        wrongUpdates.forEach { wrongUpdate ->
            val newOrder = wrongUpdate.value.toMutableList()
            val relevantRules = mutableListOf<Int>()
            rules.forEach { rule ->
                if (wrongUpdate.value.containsAll(rule.toList())) {
                    relevantRules.add(rule.first)
                }
            }
            val answerUpdates = mutableListOf<Int>()
            val sortedRules = relevantRules.sortByFrequency()
            sortedRules.forEach { firstPage ->
                if (newOrder.remove(firstPage)) {
                    answerUpdates.add(firstPage)
                }
            }
            answerUpdates.addAll(newOrder)

            total += answerUpdates[answerUpdates.size/2]
        }
        return total.toString()
    }
    fun <T> List<T>.sortByFrequency(): List<T> {
        val frequencyMap = this.groupingBy { it }.eachCount()
        return this.sortedByDescending { frequencyMap[it] }
    }
}