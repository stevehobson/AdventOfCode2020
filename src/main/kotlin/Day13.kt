class Day13(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/13

    override fun id() = 13

    private val now = inputAsLines.first().toLong()
    private val buses = inputAsLines.last().split(',').map{it.toLongOrNull()}

    data class Bus(val schedule: Long, val offset: Long)

    override fun part1(): String {
        return buses
            .filterNotNull()
            .map {(now/it +1)*it - now to it}
            .minByOrNull { (time,_) -> time }
            ?.let { //(time,bus)
                it.first * it.second
            }.toString()
    }

    override fun part2(): String {
        val busesAndOffsets =  buses
            .mapIndexedNotNull{i,b -> if (b==null) null else Bus(b,i.toLong())}
            .sortedByDescending { it.schedule }
        var step = busesAndOffsets.first().schedule
        var time = busesAndOffsets.first().schedule - busesAndOffsets.first().offset
        busesAndOffsets.drop(1).forEach { (bus, offset) ->
            while ((time + offset) % bus !=0L) {
                time += step
            }
            step *= bus
        }
        return time.toString()
    }
}