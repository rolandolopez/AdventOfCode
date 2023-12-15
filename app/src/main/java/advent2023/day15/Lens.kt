package advent2023.day15

class Lens (val label: String, val focalLength: Int) {

    override fun toString(): String {
        return "$label $focalLength"
    }
}