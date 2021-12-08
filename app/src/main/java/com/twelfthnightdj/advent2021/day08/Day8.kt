package com.twelfthnightdj.advent2021.day08

import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.InputHelpers

class Day8 : AocDays() {

    override fun partA(): String {
        val outputValue = input
            .map { it.split(" | ")[1] }
            .map { it.split(" ").count { it.length <= 4 || it.length == 7 } }
            .sum()

        return "$outputValue"
    }

    val trialInput = InputHelpers.getListOfStringsFromFile("/day08trial.txt")
    val input = InputHelpers.getListOfStringsFromFile("/day08.txt")
}
