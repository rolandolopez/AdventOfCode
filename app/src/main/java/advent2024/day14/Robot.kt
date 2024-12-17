package advent2024.day14

import utils.Quadrant

class Robot (val x: Int, val y: Int, private val dx: Int, private val dy: Int) {
    private var currentX = x
    private var currentY = y
    private var _maxX = 0
    private var _maxY = 0

    fun move(seconds: Int, maxX: Int, maxY: Int) {
        _maxX = maxX
        _maxY = maxY
        currentX += (seconds * dx)
        currentY += (seconds * dy)

        while (currentX < 0) {
            currentX += (100 * maxX)
        }
        while (currentY < 0) {
            currentY += (100 * maxY)
        }

        currentX %= maxX
        currentY %= maxY
    }

    val quadrant: Quadrant
        get() {
            if (currentX == (_maxX / 2) || currentY == (_maxY / 2)) return Quadrant.BORDER
            return if (currentX < (_maxX / 2)) {
                if (currentY < (_maxY / 2)) {
                    Quadrant.TL
                } else  {
                    Quadrant.BL
                }
            } else {
                if (currentY < (_maxY / 2)) {
                    Quadrant.TR
                } else {
                    Quadrant.BR
                }
            }
        }

    override fun toString(): String {
        return "($currentX, $currentY)"
    }
}