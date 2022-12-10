package advent2022.day09

class MegaSnake(var x: Int, var y: Int, var nodes: Int) {
     val all = mutableListOf<Snake>()
    init {
        repeat(nodes) {
            all.add(Snake(0,0))
        }
    }

    fun moveHead(instruction: Pair<String, Int>) {
        when (instruction.first) {
            "U" -> moveUp(instruction.second)
            "D" -> moveDown(instruction.second)
            "R" -> moveRight(instruction.second)
            "L" -> moveLeft(instruction.second)
        }
    }

    private fun moveUp(distance: Int) {
        repeat(distance) {
            repeat(nodes) { s ->
                if (s == 0) {
                    all[s].moveUp(1)
                }
                if (s > 0) {
                    all[s].moveToPoint(all[s-1].tail)
                }
            }
        }
    }

    private fun moveDown(distance: Int) {
        repeat(distance) {
            repeat(nodes) { s ->
                if (s == 0) {
                    all[s].moveDown(1)
                }
                if (s > 0) {
                    all[s].moveToPoint(all[s-1].tail)
                }
            }
        }
    }

    private fun moveRight(distance: Int) {
        repeat(distance) {
            repeat(nodes) { s ->
                if (s == 0) { all[s].moveRight(1) }
                if (s > 0) {
                    all[s].moveToPoint(all[s-1].tail)
                }
            }
        }
    }

    private fun moveLeft(distance: Int) {
        repeat(distance) {
            repeat(nodes) { s->
                if (s == 0) { all[s].moveLeft(1) }
                if (s > 0) {
                    all[s].moveToPoint(all[s-1].tail)
                }
            }
        }
    }

    fun printAll() {
        all.forEachIndexed { index, s ->
            println("$index: head: ${s.head}, tail: ${s.tail}")
        }
    }
    fun finalCheck() = all.last().tailTrack.size.toString()
}