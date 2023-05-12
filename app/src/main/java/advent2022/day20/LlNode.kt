package advent2022.day20

class LlNode(val key: Int) {

    var next: LlNode? = null
    var prev: LlNode? = null

    override fun toString(): String {
        return key.toString()
    }

}