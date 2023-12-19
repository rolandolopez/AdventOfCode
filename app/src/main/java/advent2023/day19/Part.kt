package advent2023.day19

class Part (val seed: String) {
    var x = 1
    var m = 1
    var a = 1
    var s = 1

    init {
        val seedlings = seed.removeSurrounding("{", "}").split(",").map { it.split("=") }
        seedlings.forEach { seedling ->
            when (seedling[0]) {
                "x" -> x = seedling[1].toInt()
                "m" -> m = seedling[1].toInt()
                "a" -> a = seedling[1].toInt()
                "s" -> s = seedling[1].toInt()
            }
        }
    }

    fun get(letter: String) =
        when (letter) {
            "x" -> x
            "m" -> m
            "a" -> a
            "s" -> s
            else -> 0
        }


    fun total() = x + m + a + s

    override fun toString(): String {
        return "Part x: $x, m: $m, a: $a, s: $s"
    }
}