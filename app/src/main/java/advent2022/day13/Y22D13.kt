package advent2022.day13

import com.twelfthnightdj.advent2021.AocDays

class Y22D13 : AocDays() {
    override var dayId = 13
    lateinit var packets: List<Packet>

    override fun setup() {
        packets = input.filter { it.isNotBlank() }.map { Packet.of(it) }
    }

    override fun partA(): String {
        return packets.chunked(2).mapIndexed { index, list ->
            if (list.first() < list.last()) index + 1 else 0
        }.sum().toString()
    }

    override fun partB(): String {
        return super.partB()
    }

    override fun reset() {
        super.reset()
    }
}

sealed class Packet : Comparable<Packet> {
    companion object {
        fun of(input: String): Packet =
            of(
                input.split("""((?<=[\[\],])|(?=[\[\],]))""".toRegex())
                    .filter { it.isNotBlank() }
                    .filter { it != "," }
                    .iterator()
            )

        private fun of(input: Iterator<String>): Packet {
            val packets = mutableListOf<Packet>()
            while(input.hasNext()) {
                when (val symbol = input.next()) {
                    "]" -> return ListPacket(packets)
                    "[" -> packets.add(of(input))
                    else -> packets.add(IntPacket(symbol.toInt()))
                }
            }
            return ListPacket(packets)
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
