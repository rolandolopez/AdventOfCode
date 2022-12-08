package advent2022.day08

import com.twelfthnightdj.advent2021.AocDays

class Y22D08 : AocDays() {
    override var dayId = 8
    private lateinit var field: IntRectArray
    var theInput = input
    override fun setup() {
        field = IntRectArray(theInput.first().length, theInput.size)
        theInput.forEachIndexed { rowNum, line ->
            line.forEachIndexed { index, c ->
                field.set(rowNum, index, c.digitToInt())
            }
        }
    }

    override fun partA(): String {
        var totalVisible = 0
        (0 until field.rows).forEach { r ->
            (0 until field.cols).forEach { c ->
                when {
                    ((r == 0) || (r >= field.rows - 1)) ||
                            ((c == 0) || (c >= field.cols - 1)) -> totalVisible++
                    lookUpA(r, c) -> totalVisible++
                    lookDownA(r, c) -> totalVisible++
                    lookLeftA(r, c) -> totalVisible++
                    lookRightA(r, c) -> totalVisible++
                }
            }
        }
        return totalVisible.toString()
    }

    private fun lookUpA(row: Int, col: Int): Boolean {
        val needle = field.get(row, col)
        for (r in (0 until row).reversed()) {
            if (field.get(r, col) >= needle) return false
        }
        return true
    }

    private fun lookDownA(row: Int, col: Int): Boolean {
        val needle = field.get(row, col)
        (row + 1 until field.rows).forEach { r ->
            if (field.get(r, col) >= needle) return false
        }
        return true
    }

    private fun lookLeftA(row: Int, col: Int): Boolean {
        val needle = field.get(row, col)
        for (c in (0 until col).reversed()) {
            if (field.get(row, c) >= needle) return false
        }
        return true
    }

    private fun lookRightA(row: Int, col: Int): Boolean {
        val needle = field.get(row, col)
        (col + 1 until field.cols).forEach { c ->
            if (field.get(row, c) >= needle) return false
        }
        return true
    }

    private fun lookUpB(row: Int, col: Int): Int {
        val needle = field.get(row, col)
        var treeCount = 0
        var currentTree: Int
        for (r in (0 until row).reversed()) {
            currentTree = field.get(r, col)
            if (currentTree < needle) {
                treeCount++
            } else {
                treeCount++
                return treeCount
            }
        }
        return treeCount
    }

    private fun lookDownB(row: Int, col: Int): Int {
        val needle = field.get(row, col)
        var treeCount = 0
        var currentTree: Int
        (row + 1 until field.rows).forEach { r ->
            currentTree = field.get(r, col)
            if (currentTree < needle) {
                treeCount++
            } else {
                treeCount++
                return treeCount
            }
        }
        return treeCount
    }

    private fun lookLeftB(row: Int, col: Int): Int {
        val needle = field.get(row, col)
        var treeCount = 0
        var currentTree: Int
        for (c in (0 until col).reversed()) {
            currentTree = field.get(row, c)
            if (currentTree < needle) {
                treeCount++
            } else {
                treeCount++
                return treeCount
            }
        }
        return treeCount
    }

    private fun lookRightB(row: Int, col: Int): Int {
        val needle = field.get(row, col)
        var treeCount = 0
        var currentTree: Int
        (col + 1 until field.cols).forEach { c ->
            currentTree = field.get(row, c)
            if (currentTree < needle) {
                treeCount++
            } else {
                treeCount++
                return treeCount
            }
        }
        return treeCount
    }

    override fun partB(): String {
        var highestCount = 0
        (0 until field.rows).forEach { r ->
            (0 until field.cols).forEach { c ->
                when {
                    ((r == 0) || (r >= field.rows - 1)) || ((c == 0) || (c >= field.cols - 1)) -> {}
                    else -> {
                        val score = lookUpB(r, c) * lookDownB(r, c) * lookLeftB(r, c) * lookRightB(r, c)
                        highestCount = maxOf(highestCount, score)
                    }
                }
            }
        }
        return highestCount.toString()
    }

    override fun reset() {
        super.reset()
    }
}