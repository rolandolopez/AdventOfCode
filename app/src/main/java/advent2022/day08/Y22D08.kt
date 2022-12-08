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
                field.set(rowNum, index,c.digitToInt() )
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
                    lookUp(r, c) -> totalVisible++
                    lookDown(r, c) -> totalVisible++
                    lookLeft(r, c) -> totalVisible++
                    lookRight(r, c) -> totalVisible++
                }
            }
        }
        return totalVisible.toString()
    }

    private fun lookUp(row: Int, col: Int): Boolean {
        val needle = field.get(row, col)
        for(r in (0 until row).reversed()) {
            if (field.get(r, col) >= needle) return false
        }
        return true
    }

    private fun lookDown(row: Int, col: Int): Boolean {
        val needle = field.get(row, col)
        (row + 1 until field.rows).forEach { r ->
            if (field.get(r, col) >= needle) return false
        }
        return true
    }

    private fun lookLeft(row: Int, col: Int): Boolean {
        val needle = field.get(row, col)
        for (c in (0 until col).reversed()) {
            if (field.get(row, c) >= needle) return false
        }
        return true
    }

    private fun lookRight(row: Int, col: Int): Boolean {
        val needle = field.get(row, col)
        (col + 1 until field.cols).forEach { c ->
            if (field.get(row, c) >= needle) return false
        }
        return true
    }

    override fun partB(): String {
        return super.partB()
    }

    override fun reset() {
        super.reset()
    }
}