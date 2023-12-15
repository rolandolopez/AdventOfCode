package advent2023.day15

import com.twelfthnightdj.advent2021.AocDays

class Y23D15 : AocDays() {
    override var dayId = 15
    var sum = 0

    override fun partA(): String {
        inputAsString.split(",").map { sequence ->
            var innerSum = 0
            innerSum = sequence.toList().fold(0) { total, c ->
                (((total + c.code) * 17) % 256)
            }
            sum += innerSum
        }
        return sum.toString()
    }

    override fun reset() {
        sum = 0
    }

    override fun partB(): String {
        var boxes = mutableMapOf<Int, MutableList<Lens>>()
        val regex = "([a-zA-Z]+)(=|-)(\\d?)".toRegex()
        inputAsString.split(",").map { sequence ->
            val match = regex.find(sequence)
            val (_, label, oper, focalLength) = match!!.groupValues
            val index = label.fold(0) { total, c ->
                (((total + c.code) * 17) % 256)

            }
            val l = (boxes.getOrPut(index) { mutableListOf() })
            if (oper == "=") {
                val firstIndex = l.indexOfFirst {
                    it.label == label
                    }
                if (firstIndex == -1) {
                    l.add(Lens(label, focalLength.toInt()))
                } else {
                    l[firstIndex] = Lens(label, focalLength.toInt())
                }
            } else {
                l.removeIf {
                    it.label == label
                }
            }
            boxes[index] = l
        }
        boxes.forEach { (idx, listOLens) ->
            listOLens.forEachIndexed { lensIndex, eachLens ->
                sum += (idx + 1) * (lensIndex + 1) * eachLens.focalLength
            }
        }
        return sum.toString()
    }
}