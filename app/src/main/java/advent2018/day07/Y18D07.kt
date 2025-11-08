package advent2018.day07

import com.twelfthnightdj.advent2021.AocDays

class Y18D07 : AocDays() {
    override var dayId = 7
    var steps: MutableMap<String, Step> = mutableMapOf()
    var sortedSteps: MutableMap<String, Step> = mutableMapOf()
    var answer: MutableList<String> = mutableListOf()

    override fun partA(): String {
        input.forEach { str ->
            val (s, e) = str.split(" must be finished before step ")
            val start = s.removePrefix("Step ")
            val end = e.removeSuffix(" can begin.")
            println("start: $start, end: $end")
            steps.putIfAbsent(end, Step(end))
            steps.putIfAbsent(start, Step(start))
            steps[end]?.addRequirement(start)
        }
        sortedSteps = steps.toSortedMap()
        steps.forEach {
            it.value.prepare()
        }

        var currentStep: Step? = sortedSteps.values.firstOrNull { it.isAvailable && !it.hasBeenCompleted }
        while (currentStep != null) {
            completeStep(currentStep)
            currentStep = sortedSteps.values.firstOrNull { it.isAvailable && !it.hasBeenCompleted }
        }

        return answer.joinToString("")
    }

    private fun completeStep(currentStep: Step) {
        answer.add(currentStep.name)
        sortedSteps.forEach {
            it.value.completedAStep(currentStep.name)
        }
    }
}