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
        return "${(gDec * eDec)}"
    }

    override fun partB(): String {
        return partBHelper(input)
    }

    private fun partBHelper(ipt: List<String>): String {
        val oxygen = criteria(ipt = ipt, checkingFor = '1', more = '1', less = '0', place = 0, default = '1')
        val scrubber = criteria(ipt = ipt, checkingFor = '1', more = '0', less = '1', place = 0, default = '0')
        val oDec = oxygen.joinToString("").toInt(2)
        val sDec = scrubber.joinToString("").toInt(2)
        return "${(oDec * sDec)}"
    }
    private fun criteria(ipt: List<String>, checkingFor: Char, more: Char, less: Char, place: Int, default: Char): List<String> {
        val onesCount = ipt.map { it[place] }.filter { it == checkingFor }.size
        val winner = when {
            (onesCount < (ipt.size / 2.0)) -> less
            (onesCount > (ipt.size / 2.0)) -> more
            else -> default
        }
        val sifted = ipt.filter { it[place] == winner }
        return if (sifted.size == 1) {
            sifted
        } else {
            criteria(
                ipt = sifted,
                checkingFor = checkingFor,
                more = more,
                less = less,
                place = place + 1,
                default = default
            )
        }
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
