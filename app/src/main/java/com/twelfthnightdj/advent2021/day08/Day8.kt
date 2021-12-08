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

    override fun partB(): String {
        return input.map { analyze(it) }.sum().toString()
    }

    private fun analyze(line: String): Int {
        val rosetta: MutableList<List<String>> = MutableList(10) { emptyList() }
        val (standard, code) = line.split(" | ").map { it.split(" ").map { it.toCharArray().sortedArray().joinToString("") } }

        rosetta[1] = standard.filter { it.length == 2 }
        rosetta[4] = standard.filter { it.length == 4 }
        rosetta[7] = standard.filter { it.length == 3 }
        rosetta[8] = standard.filter { it.length == 7 }
        rosetta[6] = standard.filter { it.length == 6 }.filterNot { it.containsAll(rosetta[1][0]) }
        rosetta[0] = standard.filter { it.length == 6 }.filterNot { it == rosetta[6][0] }.filterNot { it.containsAll(rosetta[4][0]) }
        rosetta[9] = standard.filter { it.length == 6 }.filterNot { it == rosetta[6][0] }.filterNot { it == rosetta[0][0] }
        rosetta[3] = standard.filter { it.length == 5 }.filter { it.containsAll(rosetta[1][0]) }
        rosetta[5] = standard.filter { it.length == 5 }.filterNot { it == rosetta[3][0] }.filter { rosetta[6][0].containsAll(it) }
        rosetta[2] = standard.filter { it.length == 5 }.filterNot { it == rosetta[3][0] }.filterNot { it == rosetta[5][0] }

        val stone = rosetta.map { it[0] }
        val dec= MutableList(4) { 0 }
        code.forEachIndexed { index, c ->
            dec[index] = stone.indexOf(c)
        }
        return dec.joinToString("").toInt()

    }

    private fun String.containsAll(needle: String): Boolean {
        val needles = needle.toList()
        val haystacks = this.toList()
        return haystacks.containsAll(needles)
    }

    val trialInput = InputHelpers.getListOfStringsFromFile("/day08trial.txt")
    val input = InputHelpers.getListOfStringsFromFile("/day08.txt")
}
