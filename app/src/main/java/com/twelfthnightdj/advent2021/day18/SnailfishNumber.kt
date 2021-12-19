package com.twelfthnightdj.advent2021.day18

class SnailfishNumber(var parent: SnailfishNumber? = null, var value: Int? = null) {
    var left: SnailfishNumber? = null
    var right: SnailfishNumber? = null

    override fun toString(): String {
        return value?.toString() ?: "[$left, $right]"
    }

    fun isSoloPair(): Boolean {
        return left?.value != null && right?.value != null
    }
    fun depth(d: Int): Int {
        return if (parent == null) d else parent!!.depth(d + 1)
    }
}
