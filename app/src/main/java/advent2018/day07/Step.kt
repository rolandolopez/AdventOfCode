package advent2018.day07

class Step (val name: String) {
    var requiredSteps: MutableSet<String> = mutableSetOf()
    var completedSteps: MutableSet<String> = mutableSetOf()
    var isAvailable = false
    var hasBeenCompleted = false
    init {

    }

    fun prepare() {
        println("preparing $name")
        if (requiredSteps.isEmpty()) {
            println("setting hasBeenCompleted to true")
            isAvailable = true
        }
    }

    fun addRequirement(s: String) {
        requiredSteps.add(s)
    }

    fun completedAStep(s: String) {
        if (name == s) {
            hasBeenCompleted = true
        } else {
            if (requiredSteps.contains(s)) {
                completedSteps.add(s)
                if (completedSteps.size == requiredSteps.size) {
                    isAvailable = true
                }
            }
        }

    }

    override fun toString(): String {
        return """
            | 
            | 
            |Step $name 
            |(Required steps: $requiredSteps
            |isAvailable: $isAvailable, hasBeenCompleted: $hasBeenCompleted""".trimMargin()
    }
}