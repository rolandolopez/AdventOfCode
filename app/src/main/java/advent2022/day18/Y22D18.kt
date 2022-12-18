package advent2022.day18

import com.twelfthnightdj.advent2021.AocDays

class Y22D18 : AocDays() {
    override var dayId = 18
    var space: MutableList<Point3d> = mutableListOf()

    override fun setup() {
        input.forEach { line ->
            space.add(line.split(",").map { it.toInt() }.let { Point3d(it[0], it[1], it[2]) })
        }
    }

    override fun partA(): String {
        space.forEachIndexed { index, cube ->
            cube.neighbors().forEach { neighbor ->
                if ((index + 1) <= space.size) {
                    if (space.subList(index, space.count()).contains(neighbor)) {
                        cube.removeFace()
                        space.find { it == neighbor }?.removeFace()
                    }
                }
            }
        }
        return space.sumOf { it.sidesExposed }.toString()
    }

    override fun partB(): String {
        return super.partB()
    }

    override fun reset() {
        super.reset()
    }
}