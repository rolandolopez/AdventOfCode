package advent2022.day07

class Directory(val name: String, val parentDirectory: Directory? = null, val level: Int = 0) {

    private var files = mutableListOf<File>()

    private val fileSize: Long
        get() = files.sumOf {
            it.size
        }

    fun addFile(f: File) {
        files.add(f)
    }

    fun addDirectory(d: Directory) {
        directories.add(d)
    }

    private var directories = mutableListOf<Directory>()

    private val directorySize: Long
        get() = directories.sumOf { it.totalSize }

    val totalSize: Long
        get() = fileSize + directorySize

    fun getDirectory(name: String): Directory? {
        return directories.first { it.name == name }
    }

    override fun toString(): String {
        println(" - $name (dir) level $level")
        files.forEach {
            println("  ${it.name} (file, size = ${it.size}")
        }
        directories.forEach {
            println("    $it")
        }

        return ""
    }
}