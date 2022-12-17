package advent2022.day13

import com.twelfthnightdj.advent2021.AocDays

class Y22D13 : AocDays() {
    override var dayId = 13
    lateinit var packets: MutableList<Pair<String, Packet>>

    override fun setup() {
        packets = input.filter { it.isNotBlank() }.map { Pair(it, Packet.of(it)) }.toMutableList()
    }

    override fun partA(): String {
        return packets.chunked(2).mapIndexed { index, list ->
            if (list.first().second < list.last().second) index + 1 else 0
        }.sum().toString()
    }

    override fun partB(): String {
        packets.add(Pair("[[2]]", Packet.of("[[2]]")))
        packets.add(Pair("[[6]]", Packet.of("[[6]]")))
        val sorted = packets.sortedBy { it.second }
        return sorted.mapIndexed { index, pair ->
            if (listOf("[[2]]", "[[6]]").contains(pair.first)) index + 1 else 0
        }.filter { it > 0 }.let {
            it[0] * it[1]
        }.toString()
    }
}

sealed class Packet : Comparable<Packet> {
    companion object {
        fun of(input: String): Packet {
            return of(
                input.split("""((?<=[\[\],])|(?=[\[\],]))""".toRegex())
                    .filter { it.isNotBlank() }
                    .filter { it != "," }
                    .iterator()
            )
        }

        private fun of(input: Iterator<String>): Packet {
            val localPackets = mutableListOf<Packet>()
            while(input.hasNext()) {
                when (val symbol = input.next()) {
                    "]" -> return ListPacket(localPackets)
                    "[" -> localPackets.add(of(input))
                    else -> localPackets.add(IntPacket(symbol.toInt()))
                }
            }
            return ListPacket(localPackets)
        }
    }
}

private class IntPacket(val amount: Int): Packet() {
    fun asList(): Packet = ListPacket(listOf(this))
    override fun compareTo(other: Packet): Int =
        when (other) {
            is IntPacket -> amount.compareTo(other.amount)
            is ListPacket -> asList().compareTo(other)
        }
}
private class ListPacket(val subPackets: List<Packet>): Packet() {
    override fun compareTo(other: Packet): Int =
        when (other) {
            is IntPacket -> compareTo(other.asList())
            is ListPacket -> subPackets.zip(other.subPackets)
                .map { it.first.compareTo(it.second) }
                .firstOrNull { it != 0 } ?: subPackets.size.compareTo(other.subPackets.size)
        }
}
