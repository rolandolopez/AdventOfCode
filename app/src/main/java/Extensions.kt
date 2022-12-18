//fun List<IntRange>.reduce(): List<IntRange> =
//    if (this.size <=1) this
//    else {
//        val sorted = this.sortedBy { it.first }
//        sorted.drop(1).fold(mutableListOf())
//    }