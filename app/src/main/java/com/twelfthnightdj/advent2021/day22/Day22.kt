package com.twelfthnightdj.advent2021.day22

import com.twelfthnightdj.advent2021.AocDays

class Day22 : AocDays() {
    override var dayId = 22

    private var cubes = mutableSetOf<String>()

    private var rules = mutableListOf<Rule>()
//    override fun partA(): String {
//        parse(input)
//        process(true)
//        return cubes.size.toString()
//    }

    override fun reset() {
        cubes.clear()
        rules.clear()
    }

    override fun partB(): String {
        parse(input)
        process(false)
        return cubes.size.toString()
    }

    private fun parse(ipt: List<String>) {
        ipt.forEach {
            val rule = Rule(it)
            rules.add(rule)
        }
    }

    private fun process(limit: Boolean) {
        println("processing ${rules.size} rules")
        rules.forEach { rule ->
            rule.flipCubes(cubes, limit)
        }
//        rules[0].flipCubes(cubes)
    }
}
