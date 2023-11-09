package com.twelfthnightdj.advent2021.day12

class Cave (val name: String) {

    val canRevisit: Boolean
        get() = name.first().isUpperCase()

    var visitedState: Y21D12.VisitedStatus = if (name in listOf("start", "end")) Y21D12.VisitedStatus.DONE else Y21D12.VisitedStatus.NEVER

    fun visit() {
        if (visitedState == Y21D12.VisitedStatus.NEVER) {
            visitedState = Y21D12.VisitedStatus.ONCE
        } else if (visitedState == Y21D12.VisitedStatus.ONCE) {
            visitedState = Y21D12.VisitedStatus.DONE
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
