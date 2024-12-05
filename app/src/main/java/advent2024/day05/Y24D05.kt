package advent2024.day05

import com.twelfthnightdj.advent2021.AocDays

class Y24D05 : AocDays() {
    override var dayId = 5
    var rules = mutableSetOf<Pair<Int, Int>>()
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
            rules.forEach { rule ->
                if (newOrder.indexOf(rule.first) > newOrder.indexOf(rule.second)) {
                    val firstIndex = newOrder.indexOf(rule.first)
                    val secondIndex = newOrder.indexOf(rule.second)
                    if (firstIndex > -1 && secondIndex > -1) {
                        println("first index: $firstIndex, second Index: $secondIndex, max size: ${newOrder.size}")
                        newOrder[secondIndex] = rule.first
                        newOrder[firstIndex] = rule.second
                    }
                }
            }
            println("adding ${newOrder[newOrder.size/2]} becase of $newOrder")
            total += newOrder[newOrder.size/2]
        }
        println("wrong updates count: ${wrongUpdates}")
        return total.toString()
    }
}