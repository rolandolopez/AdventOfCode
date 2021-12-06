package com.twelfthnightdj.advent2021.day06

class Fish (start: Int = 8) {
    var currentState = start
    val resetTo = 6
    fun nextDay(): Boolean {
        if (currentState == 0) {
            currentState = resetTo
            return true
        } else {
            currentState--
            return false
        }
    }

    override fun toString(): String {
        return currentState.toString()
    }
}
