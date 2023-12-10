package advent2023.day05

import com.twelfthnightdj.advent2021.AocDays
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Long.min

class Y23D05 : AocDays() {
    override var dayId = 5
    var seeds = mutableListOf<Long>()
    var activeMap = mutableMapOf<LongRange, LongRange>()
    var seedToSoilMap = mutableMapOf<LongRange, LongRange>()
    var soilToFertilizerMap = mutableMapOf<LongRange, LongRange>()
    var fertilizerToWaterMap = mutableMapOf<LongRange, LongRange>()
    var waterToLightMap = mutableMapOf<LongRange, LongRange>()
    var lightToTemperatureMap = mutableMapOf<LongRange, LongRange>()
    var temperatureToHumidityMap = mutableMapOf<LongRange, LongRange>()
    var humidityToLocationMap = mutableMapOf<LongRange, LongRange>()

    override fun setup() {
        var mapIndex = 0
        input.forEachIndexed { index, line ->
            if (index == 0) {
                seeds = line.split(": ")[1].split(" ").map { it.toLong() }.toMutableList()
                return@forEachIndexed
            }
            if (line.contains(" map:")) return@forEachIndexed
            if (line.isEmpty()) {
                when(mapIndex) {
                    0 -> {}
                    1 -> seedToSoilMap = activeMap.toMutableMap()
                    2 -> soilToFertilizerMap = activeMap.toMutableMap()
                    3 -> fertilizerToWaterMap = activeMap.toMutableMap()
                    4 -> waterToLightMap = activeMap.toMutableMap()
                    5 -> lightToTemperatureMap = activeMap.toMutableMap()
                    6 -> temperatureToHumidityMap = activeMap.toMutableMap()
                }
                if (mapIndex <= 6) activeMap.clear()
                mapIndex++
                return@forEachIndexed
            }
            val (destStart, sourceStart, rangeLength) = line.split(" ").map { it.toLong() }
            val sourceRange = sourceStart until sourceStart + rangeLength
            val destRange = destStart until destStart + rangeLength
            activeMap[sourceRange] = destRange

        }
        humidityToLocationMap = activeMap.toMutableMap()

    }

    override fun partA(): String {
        println("humidity to location map: $humidityToLocationMap")

        GlobalScope.launch {
            val lowestLocation = plantSeed()
            println("lowest location: $lowestLocation")
        }
        return "check logcat for answer"//lowestLocation.toString()
    }

    private suspend fun plantSeed() {
        coroutineScope {
            var min = -1L
            seeds.forEach { seed ->
                println("seed: $seed")
                val soilNumber = findInMap(seed, seedToSoilMap)
                println("soilNumber: $soilNumber")
                val fertilizerNumber = findInMap(soilNumber, soilToFertilizerMap)
                println("fertilizerNumber: $fertilizerNumber")
                val waterNumber = findInMap(fertilizerNumber, fertilizerToWaterMap)
                println("waterNumber: $waterNumber")
                val lightNumber = findInMap(waterNumber, waterToLightMap)
                println("lightNumber: $lightNumber")
                val temperatureNumber = findInMap(lightNumber, lightToTemperatureMap)
                println("temperature number: $temperatureNumber")
                val humidityNumber = findInMap(temperatureNumber, temperatureToHumidityMap)
                println("humidityNumber: $humidityNumber")
                val locationNumber = findInMap(humidityNumber, humidityToLocationMap)
                println("locationNumber: $locationNumber")
                if (min == -1L) min = locationNumber
                min = min(min, locationNumber)
            }
            println("lowest location $min")
        }
    }
    private fun findInMap(num: Long, m: Map<LongRange, LongRange>): Long {
        val plotCount = m.filterKeys { key -> key.contains(num) }
        val plotNumber = if (plotCount.isEmpty()) {
            num
        } else {
            var n = 0L
            plotCount.forEach{
                n = it.value.elementAt(it.key.indexOf(num))
            }
            n
        }
        return plotNumber
    }

    override fun partB(): String {
        return super.partB()
    }
}