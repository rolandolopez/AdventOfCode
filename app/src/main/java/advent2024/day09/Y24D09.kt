package advent2024.day09

import com.twelfthnightdj.advent2021.AocDays

class Y24D09 : AocDays() {
    override var dayId = 9
    private var frontIndex = 0
    private var rearIndex = 1
    private var skipperIndex = 1
    private var leftInRear = 0
    private var numberInRear = 0
    private var positionMultiplier = 0
    private var expectedLength = 0
    private var total: Long = 0
    private var end = ""
    private var files = ""
    private var spacers = ""

    override fun setup() {
        println("input as string: ${inputAsString.length}")
        println("regular input: ${input.size}")
        println("input at 2: ${i(2)}")
        println("final: ${inputAsString.elementAt(inputAsString.length - 1)}")
        for (ndx in inputAsString.indices) {
            if (ndx % 2 == 0) {
                val n = i(ndx)
                expectedLength += n
                files += n
            } else {
                spacers += i(ndx)
            }
        }
        println("expected length: $expectedLength")
        super.setup()
    }

    override fun reset() {
        frontIndex = 0
        rearIndex = files.length
        skipperIndex = 0
        leftInRear = 0
        numberInRear = 0
        positionMultiplier = 0
        total = 0L
        end = ""
        super.reset()
    }

    override fun partA(): String {
//        frontIndex = 0
//        rearIndex = inputAsString.length + 1
//        println("front/rear/skip = $frontIndex/$rearIndex/$skipperIndex")
////        while(skipperIndex < rearIndex) {
//        while(frontIndex <= rearIndex) {
//            println("front index: $frontIndex, repeating ${i(frontIndex)} times")
//            repeat(i(frontIndex)) {
//                val digit = ((frontIndex) / 2)
//                println(" ($positionMultiplier) * $digit")
//                total += positionMultiplier * digit
//                end += digit.toString()
//                positionMultiplier++
//                println(" end of first repeat front/rear/skip = $frontIndex/$rearIndex/$skipperIndex")
//            }
//            frontIndex += 2
//
//            println("skipper index: $skipperIndex")
//            repeat(i(skipperIndex)) {
//                println("skip: $skipperIndex, repeating ${i(skipperIndex)} times")
//                val popped = popFromRear()
////                if (frontIndex > rearIndex) return@repeat
//                println("  ($positionMultiplier) * $popped")
//                total += positionMultiplier * popped
//                end += popped.toString()
//                positionMultiplier++
//                println("end of second repeat front/rear/skip = $frontIndex/$rearIndex/$skipperIndex")
//            }
//
//            skipperIndex += 2
//        }
//        println("end of it all front/rear/skip = $frontIndex/$rearIndex/$skipperIndex")
//        println("end: $end")
        return total.toString()
    }

    override fun partB(): String {
        println("files: $files")
        println("space: $spacers")
        while(end.length < expectedLength) {
//        while(frontIndex <= rearIndex) {
            repeat(f(frontIndex)) {
//                println("f ($positionMultiplier) * $frontIndex")
                end += frontIndex
//                println("end: $end")
                total += positionMultiplier * frontIndex
                if (end.length >= expectedLength) return total.toString().also { println("end: $end") }
                positionMultiplier++
            }
            frontIndex++
            repeat(s(skipperIndex)) { count ->
//                println("s $count/${s(skipperIndex)}")
                val popped = popFromRear()
                if (popped == -1) return total.toString().also { println("end: $end") }
//                println("r ($positionMultiplier) * $popped")
                end += popped
//                println("end: $end")
                total += positionMultiplier * popped
                if (end.length >= expectedLength) return total.toString().also { println("end: $end") }
                positionMultiplier++
            }
            skipperIndex++

        }
        println("end: $end")
        return total.toString()
    }

    private fun popFromRear(): Int {
//        while (leftInRear == 0) {
//            rearIndex -= 2
//            leftInRear = i(rearIndex)
//            numberInRear = (rearIndex)/2
//        }
//        leftInRear--
//        return numberInRear
        while (leftInRear == 0) {
            rearIndex--
//            println("f/r: $frontIndex/$rearIndex")
            if (frontIndex > rearIndex) return -1
            leftInRear = f(rearIndex)
            numberInRear = rearIndex
//            println("left in rear: $leftInRear, number in rear: $numberInRear")
        }
        leftInRear--
//        println("reducing left in rear to $leftInRear and returning: $numberInRear")
        return numberInRear
    }

    private fun i(index: Int): Int {
        return inputAsString.elementAt(index).toString().toInt()
    }
    private fun f(index: Int): Int {
        return files.elementAt(index).toString().toInt()
    }
    private fun s(index: Int): Int {
        return spacers.elementAt(index).toString().toInt()
    }

}