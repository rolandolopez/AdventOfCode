package com.twelfthnightdj.advent2021.day20

import com.twelfthnightdj.advent2021.AocDays

class Day20 : AocDays() {

    override var dayId = 20
    var rosetta = mutableListOf<Char>()
    private lateinit var image: MutableList<MutableList<Char>>
    var maxX = 0
    var maxY = 0
    override fun partA(): String {
        process(input)
        maxX = image.size
        maxY = image[0].size
        enhance(2)
        return "${countIt()}"
    }

    private fun enhance(t: Int) {
        println("image size: ${image.size} x ${image[0].size}")
        image.prettyPrint()
        repeat(t) {
            pad(image)
            println("image size: ${image.size} x ${image[0].size}")
            maxY = image.size
            maxX = image[0].size
            val enhancement = MutableList(image[0].size) { MutableList(image.size) { '.' } }
            image.forEachIndexed { x, list ->
                list.forEachIndexed { y, _ ->
                    val dec = convertToDecimal(x, y)

                    enhancement[y][x] = rosetta[dec]
                }
            }
            image.prettyPrint()
            image = enhancement
            image.prettyPrint()
        }
    }

    private fun pad(image: MutableList<MutableList<Char>>) {
        image.add(0, MutableList(image[0].size) { '.' } )
        image.add(0, MutableList(image[0].size) { '.' } )
        image.add(MutableList(image[0].size) { '.' } )
        image.add(MutableList(image[0].size) { '.' } )
        image.forEach {
            it.add(0, '.')
            it.add(0, '.')
            it.add('.')
            it.add('.')
        }
    }

    private fun convertToDecimal(x: Int, y: Int): Int {
        var bin = ""
        // y - 1
        if (y - 1 < 0) {
            bin = "000"
        } else {
            if (x - 1 < 0) {
                bin += "0"
                bin += if (image[y - 1][x] == '#') "1" else "0"
            } else {
                bin += if (image[y - 1][x - 1] == '#') "1" else "0"
                bin += if (image[y - 1][x] == '#') "1" else "0"
            }
            if (x + 1 >= maxX){
                bin += "0"
            } else {
                bin += if (image[y - 1][x + 1] == '#') "1" else "0"
            }
        }

        // y == 0
        if (x - 1 < 0) {
            bin += "0"
            bin += if (image[y][x] == '#') "1" else "0"
        } else {
            bin += if (image[y][x - 1] == '#') "1" else "0"
            bin += if (image[y][x] == '#') "1" else "0"
        }
        if (x + 1 >= maxX) {
            bin += "0"
        } else {
            bin += if (image[y][x + 1] == '#') "1" else "0"
        }

        // y + 1
        if (y + 1 >= maxY) {
            bin = "000"
        } else {
            if (x - 1 < 0) {
                bin += "0"
                bin += if (image[y + 1][x] == '#') "1" else "0"
            } else {
                bin += if (image[y + 1][x - 1] == '#') "1" else "0"
                bin += if (image[y + 1][x] == '#') "1" else "0"
            }
            if (x + 1 >= maxX){
                bin += "0"
            } else {
                bin += if (image[y + 1][x + 1] == '#') "1" else "0"
            }
        }
        return bin.toInt(2)
    }

    private fun countIt() =
        image.flatten().count { it == '#' }


    private fun process(ipt: List<String>) {
        rosetta = ipt[0].toCharArray().toMutableList()
        image = ipt.drop(2).map { it.toCharArray().toMutableList() }.toMutableList()
    }
    private fun MutableList<MutableList<Char>>.prettyPrint() {
        println("x: ${this.size}")
        this.forEach {
            println(it.joinToString(""))
        }
    }
}
