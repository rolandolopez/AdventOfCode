package advent2022.day18

class Point3d (val x: Int, val y: Int, val z:Int) {

    var sidesExposed = 6

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point3d
        return x == other.x && y == other.y && z == other.z
    }

    override fun toString(): String {
        return "Point 3d ($x, $y, $z)"
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + z
        return result
    }

    fun removeFace() = sidesExposed--

    fun neighbors(): Set<Point3d> =
        setOf(
            Point3d(x - 1, y, z),
            Point3d(x + 1, y, z),
            Point3d(x , y - 1, z),
            Point3d(x , y + 1, z),
            Point3d(x, y, z - 1),
            Point3d(x, y, z + 1)
        )
}