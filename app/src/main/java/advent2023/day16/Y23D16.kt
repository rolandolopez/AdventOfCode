package advent2023.day16

import com.twelfthnightdj.advent2021.AocDays
import utils.Direction

class Y23D16 : AocDays() {
    override var dayId = 16
    var field = mutableMapOf<String, LaserSquare>()
    var beams = mutableListOf<Beam>()
    var beamsToAdd = mutableListOf<Beam>()
    var xMax = 0
    var yMax = 0

    override fun partA(): String {
        xMax = input[0].length
        yMax = input.size
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                field.putIfAbsent("$x#$y", LaserSquare(x, y, c))
            }
        }
        println("field: ${field.count()}")
        beams.add(Beam(-1, 0, Direction.EAST))
        println("xMax, yMax: $xMax, $yMax")
        while (beams.isNotEmpty()) {
            beams.forEach { beam ->
                beam.move()
            }
            beams.removeAll { it.shouldRemove }
            beams.addAll(beamsToAdd)
            beamsToAdd.clear()
        }

        return field.filterValues {
            it.isEnergized
        }.size.toString()
    }


    inner class Beam(
        var x: Int,
        var y: Int,
        var heading: Direction
    ) {
        var shouldRemove = false

        fun move() {
            when (heading) {
                Direction.NORTH -> y--
                Direction.EAST -> x++
                Direction.SOUTH -> y++
                Direction.WEST -> x--
            }
            if (checkForBoundary()) {
                val square = this@Y23D16.field["$x#$y"] ?: return
                square.isEnergized = true
                when {
                    square.passedHeading.contains(heading) -> {
                        this.shouldRemove = true
                    }

                    square.isSpace() -> {
                        square.passedHeading.add(heading)
                    }

                    square.isHorizontalMirror() && travellingVertically() -> {
                        val leftBeam = Beam(x, y, Direction.WEST)
                        val rightBeam = Beam(x, y, Direction.EAST)
                        beamsToAdd.addAll(listOf(leftBeam, rightBeam))
                        square.passedHeading.add(heading)
                        this.shouldRemove = true
//                        println("           at a horizontalMirror $square")
                    }

                    square.isVerticalMirror() && travellingHorizontally() -> {
                        val upBeam = Beam(x, y, Direction.NORTH)
                        val downBeam = Beam(x, y, Direction.SOUTH)
                        beamsToAdd.addAll(listOf(upBeam, downBeam))
                        square.passedHeading.add(heading)
                        this.shouldRemove = true
//                        println("           at a verticalMmirror $square")

                    }

                    square.isDemocraticMirror() -> { // '\'
                        square.passedHeading.add(heading)
                        this.heading = when (heading) {
                            Direction.NORTH -> Direction.WEST
                            Direction.EAST -> Direction.SOUTH
                            Direction.SOUTH -> Direction.EAST
                            Direction.WEST -> Direction.NORTH
                        }

//                        println("            turning at a democratic mirror $square")
                    }

                    square.isRepublicanMirror() -> { // '/'
                        square.passedHeading.add(heading)
                        this.heading = when (heading) {
                            Direction.NORTH -> Direction.EAST
                            Direction.EAST -> Direction.NORTH
                            Direction.SOUTH -> Direction.WEST
                            Direction.WEST -> Direction.SOUTH
                        }
//                        println("            turning at a republican mirror $square")
                    }
                }
            }
        }

        private fun travellingHorizontally() =
            heading == Direction.EAST || heading == Direction.WEST

        private fun travellingVertically() =
            heading == Direction.NORTH || heading == Direction.SOUTH

        private fun checkForBoundary(): Boolean {
            return when {
                x > xMax ||
                        x < 0 ||
                        y < 0 ||
                        y > yMax -> {
                    this.shouldRemove = true
                    false
                }

                else -> true

            }
        }

        override fun toString(): String {
            return "Beam at ($x, $y) heading: $heading"
        }
    }
}