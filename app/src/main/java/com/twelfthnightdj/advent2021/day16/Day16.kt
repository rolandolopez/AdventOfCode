package com.twelfthnightdj.advent2021.day16

import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.InputHelpers
import java.util.*

class Day16 : AocDays() {

    private var currentIndex = 0
    private var bin = ""
    private var versionSum = 0L
    override fun partA(): String {
        bin = input.toBin()
        startReadingPacket()
        return versionSum.toString()
    }

    private fun startReadingPacket() {
        val versionNumber = bin.sliceIt(3).toLong(2)
        versionSum += versionNumber
        val typeId = bin.sliceIt(3).toLong(2)
        if (typeId == 4L) {
            val literal = treatAsLiteralString()
        }
        else {
            val operator = treatAsOperator()
        }
    }

    private fun treatAsLiteralString(): Long {
        var literal = ""
        while(true) {
            val group = bin.sliceIt(5)
            literal += group.substring(1)
            if (group.first() == '0') {
                return literal.toLong(2)
            }
        }
    }

    private fun treatAsOperator() {
        val lengthTypeId = bin.sliceIt(1)
        if (lengthTypeId == "0") {
            val totalBits = bin.sliceIt(15).toLong(2)
            val startingIndex = currentIndex
            while(currentIndex + 1 < startingIndex + totalBits) {
                startReadingPacket()
            }
        } else {
            val totalPackets = bin.sliceIt(11).toInt(2)
            repeat(totalPackets) {
                startReadingPacket()
            }
        }
    }

    private fun String.sliceIt(l: Int): String {
        val ret = this.substring(currentIndex, currentIndex + l)
        currentIndex += l
        return ret
    }

    private fun String.toBin(): String {
        return this.toCharArray().map { hexToBin(it) }.joinToString("")
    }

    private fun hexToBin(hex: Char): String = hexToBin(hex.toString())

    private fun hexToBin(hex: String): String =
        when (hex.uppercase(Locale.getDefault())) {
            "0" -> "0000"
            "1" -> "0001"
            "2" -> "0010"
            "3" -> "0011"
            "4" -> "0100"
            "5" -> "0101"
            "6" -> "0110"
            "7" -> "0111"
            "8" -> "1000"
            "9" -> "1001"
            "A" -> "1010"
            "B" -> "1011"
            "C" -> "1100"
            "D" -> "1101"
            "E" -> "1110"
            "F" -> "1111"
            else -> hex
        }

    val trialInput = InputHelpers.getContentsFromFile("/day16trial.txt")
    private val input = InputHelpers.getContentsFromFile("/day16.txt")
}
