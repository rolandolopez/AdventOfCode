package advent2022.day07

import com.twelfthnightdj.advent2021.AocDays

class Y22D07 : AocDays() {
    override var dayId = 7

    private val rootDirectory = Directory("/")
    var currentDirectory = rootDirectory
    var allDirectories = mutableListOf<Directory>()

    override fun setup() {
        allDirectories.add(rootDirectory)
        super.setup()
    }

    override fun partA(): String {
        input.forEach { line ->
            when {
                line.startsWith("$ ") -> processUserInput(line)
                else -> processOther(line)
            }
        }
        var totalOver = 0L
        var lessThan = 0
        allDirectories.forEach { d ->
            val tot = d.totalSize
            if (tot <= 100000L) {
                totalOver += tot
                lessThan++
                println("d size (${d.name}): $tot  <---------")
            } else {
                println("d size (${d.name}): $tot")
            }
        }
        println("directories: ${allDirectories.size}")
        println("less than 100000: $lessThan")
        return totalOver.toString()
    }

    private fun processUserInput(line: String) {
        val inputs = line.split(" ")
        when (inputs[1]) {
            "cd" -> {
                currentDirectory = when (inputs[2]) {
                    ".." -> currentDirectory.parentDirectory ?: rootDirectory
                    "/" -> rootDirectory
                    else -> currentDirectory.getDirectory(inputs[2]) ?: rootDirectory
                }
            }
            "ls" -> {}

        }
    }

    private fun processOther(line: String) {
        val inputs = line.split(" ")
        when(inputs[0]) {
            "dir" -> {
                val directory = Directory(inputs[1], currentDirectory, currentDirectory.level + 1)
                allDirectories.add(directory)
                currentDirectory.addDirectory(directory)
            }
            else -> currentDirectory.addFile(File(inputs[1], inputs[0].toLong() ))
        }
    }

    override fun partB(): String {
        return super.partB()
    }

    override fun reset() {
        super.reset()
    }
}