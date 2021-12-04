package com.twelfthnightdj.advent2021.day04

class Card (numbers: List<Int>) {
    var field = numbers.toMutableList()
    var total = 0

    init {
        total = field.sum()
    }

    fun numberCalled(called: Int): Long? {
        val position = field.indexOf(called)
        if (position < 0) return null
        total -= called
        field[position] = -1
        if (isWinner()) {
            return (total * called).toLong()
        } else {
            return null
        }
    }

    fun isWinner() =
        (field[0]  + field[1]  + field[2]  + field[3]  + field[4] == -5) ||
        (field[5]  + field[6]  + field[7]  + field[8]  + field[9]  == -5) ||
        (field[10] + field[11] + field[12] + field[13] + field[14] == -5) ||
        (field[15] + field[16] + field[17] + field[18] + field[19]  == -5) ||
        (field[20] + field[21] + field[22] + field[23] + field[24] == -5) ||
        (field[0]  + field[5]  + field[10]  + field[15]  + field[20] == -5) ||
        (field[1]  + field[6]  + field[11]  + field[16]  + field[21] == -5) ||
        (field[2]  + field[7]  + field[12]  + field[17]  + field[22] == -5) ||
        (field[3]  + field[8]  + field[13]  + field[18]  + field[23] == -5) ||
        (field[4]  + field[9]  + field[14]  + field[19]  + field[24] == -5)

    override fun toString(): String {
        return field.toString()
    }
}
