package com.twelfthnightdj.advent2021.day22

class Rule (val starter: String) {
    val directive = starter.split(" ")[0]

    private val ranges = starter.split(" ")[1].split(",")
    val xMin = ranges[0].split("=")[1].split("..")[0].toInt()
    val xMax = ranges[0].split("=")[1].split("..")[1].toInt()
    val yMin = ranges[1].split("=")[1].split("..")[0].toInt()
    val yMax = ranges[1].split("=")[1].split("..")[1].toInt()
    val zMin = ranges[2].split("=")[1].split("..")[0].toInt()
    val zMax = ranges[2].split("=")[1].split("..")[1].toInt()

    fun flipCubes(cubes: MutableSet<String>, limit: Boolean) {
        if (limit) {
            if (xMin in (-50..50) && xMax in (-50..50) && yMin in (-50..50) && yMax in (-50..50) && zMin in (-50..50) && zMax in (-50..50)) {
                for (x in xMin..xMax) {
                    for (y in yMin..yMax) {
                        for (z in zMin..zMax) {
                            val key = "$x,$y,$z"
                            if (directive == "on") {
                                cubes.add(key)
                            } else {
                                cubes.remove(key)
                            }
                        }
                    }
                }
            }
        } else {
            for (x in xMin..xMax) {
                for (y in yMin..yMax) {
                    for (z in zMin..zMax) {
                        val key = "$x,$y,$z"
                        if (directive == "on") {
                            cubes.add(key)
                        } else {
                            cubes.remove(key)
                        }
                    }
                }
            }
        }
    }

    override fun toString(): String {
        return "Turn $directive ($xMin, $xMax), ($yMin, $yMax), ($zMin, $zMax)"
    }
}
