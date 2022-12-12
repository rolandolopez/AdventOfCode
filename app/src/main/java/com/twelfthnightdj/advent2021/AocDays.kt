package com.twelfthnightdj.advent2021

import com.twelfthnightdj.advent2021.util.InputHelpers

abstract class AocDays {
    open var dayId = 0
    var useTrialInput = false
    open var yearId = 22
    open fun setup() {}
    open fun partA(): String = ""
    open fun partB(): String = ""
    open fun reset() {}

    fun loadTrialInputAsString(): String {
        return InputHelpers.getContentsFromFile("/${yearId}/day${dayId}trial.txt")
    }
    fun loadInputAsString(): String {
        return InputHelpers.getContentsFromFile("/${yearId}/day${dayId}.txt")
    }
    fun loadTrialInputAsList(): List<String> {
        return InputHelpers.getListOfStringsFromFile("/${yearId}/day${dayId}trial.txt")
    }
    fun loadInputAsList(): List<String> {
        return InputHelpers.getListOfStringsFromFile("/${yearId}/day${dayId}.txt")
    }

    val inputAsString
        get() = if (useTrialInput) {
            loadTrialInputAsString()
        } else {
            loadInputAsString()
        }

    val input
        get() = if (useTrialInput) {
            loadTrialInputAsList()
        } else {
            loadInputAsList()
        }
}
