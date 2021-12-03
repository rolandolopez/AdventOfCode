package com.twelfthnightdj.advent2021

import com.twelfthnightdj.advent2021.util.InputHelpers

class Day3 : AocDays() {
    override fun partA(): String {
        return partAHelper(input)
    }

    private fun partAHelper(ipt: List<String>): String {
        val gamma = IntArray(ipt[0].length)
        val epsilon = IntArray(ipt[0].length)
        (ipt[0].indices).forEach { place ->
            val g = if (ipt.map { it[place] }.filter { it == '1' }.size > (ipt.size / 2)) 1 else 0
            gamma[place] = g
            epsilon[place] = if (g == 1) 0 else 1
        }
        val gDec = gamma.asList().joinToString("").toInt(2)
        val eDec = epsilon.asList().joinToString("").toInt(2)
        println("answer: ${(gDec * eDec)}")
        return "${(gDec * eDec)}"
    }

    val trialInput = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    val input = InputHelpers.getListOfStringsFromFile("/day03.txt")
}
