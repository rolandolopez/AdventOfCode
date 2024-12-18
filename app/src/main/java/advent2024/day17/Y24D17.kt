package advent2024.day17

import com.twelfthnightdj.advent2021.AocDays
import kotlin.math.pow
import kotlin.math.truncate

class Y24D17 : AocDays() {
    override var dayId = 17
    private var currentCommand = 0
    private var registerA = 0.0
    private var registerB = 0.0
    private var registerC = 0.0
    private var program = mutableListOf<Pair<Int, Int>>()
    private var output = mutableListOf<Int>()

    override fun setup() {
        input.forEachIndexed { index, line ->
            when (index) {
                0 -> registerA = line.split(": ")[1].toDouble()
                1 -> registerB = line.split(": ")[1].toDouble()
                2 -> registerC = line.split(": ")[1].toDouble()

                4 -> {
                    val code = line.split(": ")[1].split(",")
                    code.chunked(2) {
                        program.add(Pair(it[0].toInt(), it[1].toInt()))
                    }
                }
            }
        }
        println("100 / 6: ${100 / 6}")
        println("100L / 6: ${100L / 6}")
        println("100.0 / 6: ${truncate(100.0 / 6).toInt().toDouble()}")
        println("100.0 / 6.0: ${100.0 / 6.0}")

        println("A: $registerA")
        println("B: $registerB")
        println("C: $registerC")
        println("program: $program")
        super.setup()
    }

    override fun partA(): String {
        while (currentCommand < program.size) {
            runCommand()
        }
        println("At the end")
        println("A: $registerA")
        println("B: $registerB")
        println("C: $registerC")
        println("output: $output")
        return output.joinToString("")
    }

    private fun runCommand() {
        val (opcode, operand) = program[currentCommand]
        val comboOp = getComboOperand(operand)
        println("${currentCommand}: code, operand, combo: $opcode, $operand, $comboOp")
        when (opcode) {
            0 -> {
                val denom = 2.0.pow(comboOp)
                println(" $registerA/2^$comboOp = $registerA / $denom")
                registerA = truncate( registerA / denom)
                println("   register A: $registerA")
            }
            1 -> {
                registerB = (registerB.toLong() xor operand.toLong()).toDouble()
                println("   register B: $registerB")
            }
            2 -> {
                registerB = comboOp % 8.0
                println("   register B: $registerB")
            }
            3 -> {
                if (registerA != 0.0) {
                    println("moving current command from $currentCommand")
                    currentCommand = operand / 2
                    println("to $currentCommand")
                    return
                } else println("no need to move")
            }
            4 -> {
                registerB = (registerB.toLong() xor registerC.toLong()).toDouble()
                println("   register B: $registerB")
            }
            5 -> {
                val adding = comboOp.toInt() % 8
                output.add(adding)
                println("outputting: $adding")
            }
            6 -> {
                val denom = 2.0.pow(comboOp)
                println(" $registerA/2^$comboOp = $registerA / $denom")
                registerB = truncate( registerA / denom)
                println("   register B: $registerB")
            }
            7 -> {
                val denom = 2.0.pow(comboOp)
                println(" $registerA/2^$comboOp = $registerA / $denom")
                registerC = truncate( registerA / denom)
                println("   register C: $registerC")
            }
        }
        currentCommand++
    }

    private fun getComboOperand(operand: Int): Double =
        when (operand) {
            0, 1, 2, 3 -> operand.toDouble()
            4 -> registerA
            5 -> registerB
            6 -> registerC
            else -> 0.0
        }

}