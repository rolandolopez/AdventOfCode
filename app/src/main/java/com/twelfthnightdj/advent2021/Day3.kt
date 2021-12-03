package com.twelfthnightdj.advent2021

import com.twelfthnightdj.advent2021.util.InputHelpers

class Day3 : AocDays() {
    override fun partA(): String {
        return partAHelper(input)
    }

    fun partAHelper(ipt: List<String>): String {
        val first = ipt.map { it.get(0) }.filter { it.equals('1') }.size
        val second = ipt.map { it.get(1) }.filter { it.equals('1') }.size
        val third = ipt.map { it.get(2) }.filter { it.equals('1') }.size
        val fourth = ipt.map { it.get(3) }.filter { it.equals('1') }.size
        val fifth = ipt.map { it.get(4) }.filter { it.equals('1') }.size
        val sixth = ipt.map { it.get(5) }.filter { it.equals('1') }.size
        val seventh = ipt.map { it.get(6) }.filter { it.equals('1') }.size
        val eighth = ipt.map { it.get(7) }.filter { it.equals('1') }.size
        val ninth = ipt.map { it.get(8) }.filter { it.equals('1') }.size
        val tenth = ipt.map { it.get(9) }.filter { it.equals('1') }.size
        val eleventh = ipt.map { it.get(10) }.filter { it.equals('1') }.size
        val twelfth = ipt.map { it.get(11) }.filter { it.equals('1') }.size
        var gamma = ""
        var epsilon = ""
        if (first > (ipt.size / 2)) {
            gamma = "1"
            epsilon = "0"
        } else {
            gamma = "0"
            epsilon = "1"
        }
        if (second > (ipt.size / 2)) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
        if (third > (ipt.size / 2)) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
        if (fourth > (ipt.size / 2)) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
        if (fifth > (ipt.size / 2)) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
        if (sixth > (ipt.size / 2)) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
        if (seventh > (ipt.size / 2)) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
        if (eighth > (ipt.size / 2)) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
        if (ninth > (ipt.size / 2)) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
        if (tenth > (ipt.size / 2)) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
        if (eleventh > (ipt.size / 2)) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
        if (twelfth > (ipt.size / 2)) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
        println("gamma: ${gamma.toInt(2)}, epsilon: ${epsilon.toInt(2)}")
        return (gamma.toInt(2) * (epsilon.toInt(2))).toString()
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
