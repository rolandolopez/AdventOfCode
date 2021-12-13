package com.twelfthnightdj.advent2021.day12

class Cave (val name: String) {

    val canRevisit: Boolean
        get() = name.first().isUpperCase()

    var visitedState: Day12.VisitedStatus = if (name in listOf("start", "end")) Day12.VisitedStatus.DONE else Day12.VisitedStatus.NEVER

    fun visit() {
        if (visitedState == Day12.VisitedStatus.NEVER) {
            visitedState = Day12.VisitedStatus.ONCE
        } else if (visitedState == Day12.VisitedStatus.ONCE) {
            visitedState = Day12.VisitedStatus.DONE
        }
    }

    fun addConnection(secondCave: Cave) {
        connections.add(secondCave)
    }

    var connections = mutableSetOf<Cave>()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cave
        return this.name == other.name
    }

    override fun toString(): String {
        return "Cave $name"// ($canRevisit)with connections to ${connections.map { it.name }}"
    }
}
