package advent2021.day18

class SnailfishNumber(var parent: SnailfishNumber? = null, var leftValue: Int? = null, var rightValue: Int? = null) {
    var left: SnailfishNumber? = null
    var right: SnailfishNumber? = null

    override fun toString(): String {
        return "[" + (leftValue?.toString() ?: left) + "," + (rightValue?.toString() ?: right) + "]"
    }

    fun isLeftChild(): Boolean =
        parent?.let {
            it.left == this
        } ?: false


    fun isRightChild(): Boolean =
        parent?.let {
            it.right == this
        } ?: false


    fun isSoloPair(): Boolean {
        return leftValue != null && rightValue != null
    }
    fun depth(d: Int = 0): Int {
        return if (parent == null) d else parent!!.depth(d + 1)
    }
}
