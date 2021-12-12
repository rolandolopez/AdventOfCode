package com.twelfthnightdj.advent2021.day12

class Cave (val name: String) {

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
        return "Cave $name with connections to ${connections.map { it.name }}"
    }
}
