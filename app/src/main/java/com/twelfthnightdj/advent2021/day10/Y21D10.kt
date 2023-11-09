package com.twelfthnightdj.advent2021.day10

import com.twelfthnightdj.advent2021.AocDays
import com.twelfthnightdj.advent2021.util.Stack

class Y21D10 : AocDays() {
    override var dayId = 10

    private val start0 = "("
    private val start1 = "["
    private val start2 = "{"
    private val start3 = "<"
    private val starters = listOf(start0, start1, start2, start3 )

    private val end0 = ")"
    private val end1 = "]"
    private val end2 = "}"
    private val end3 = ">"

    var illegals = mutableListOf(0,0,0,0)

    override fun partA(): String {
        input.forEach input@ { line ->
            val stack = Stack<String>()
            line.toList().forEach {
                if (starters.contains(it)) {
                    stack.push(it)
                } else {
                    when (stack.peek()) {
                        start0 -> if (it == end0) stack.pop() else {
                            markAsIllegal(it)
                            return@input
                        }
                        start1 -> if (it == end1) stack.pop() else {
                            markAsIllegal(it)
                            return@input
                        }
                        start2 -> if (it == end2) stack.pop() else {
                            markAsIllegal(it)
                            return@input
                        }
                        start3 -> if (it == end3) stack.pop() else {
                            markAsIllegal(it)
                            return@input
                        }
                    }
                }
            }
        }
        val sum = (illegals[0] * 3) + (illegals[1] * 57) + (illegals[2] * 1197) + (illegals[3] * 25137)
        return "$sum"
    }

    override fun partB(): String {
        val totalPoints = mutableListOf<Long>()
        input.forEach input@ { line ->
            val stack = Stack<String>()
            line.toList().forEach {
                if (starters.contains(it)) {
                    stack.push(it)
                } else {
                    when (stack.peek()) {
                        start0 -> if (it == end0) stack.pop() else {
                            return@input
                        }
                        start1 -> if (it == end1) stack.pop() else {
                            return@input
                        }
                        start2 -> if (it == end2) stack.pop() else {
                            return@input
                        }
                        start3 -> if (it == end3) stack.pop() else {
                            return@input
                        }
                    }
                }
            }
            var running = 0L
            while (!stack.isEmpty()) {
                val point = when(stack.pop()) {
                    start0 -> 1L
                    start1 -> 2L
                    start2 -> 3L
                    start3 -> 4L
                    else -> 1L
                }
                running = (5L * running) + point
            }
            totalPoints.add(running)
        }

        val middle = (totalPoints.size - 1) / 2
        return totalPoints.sorted()[middle].toString()
    }
    private fun markAsIllegal(bad: String) {
        when(bad) {
            end0 -> illegals[0]++
            end1 -> illegals[1]++
            end2 -> illegals[2]++
            end3 -> illegals[3]++
        }
    }

    private fun String.toList(): List<String> {
        return this.toCharArray().map { it.toString() }
    }
}
