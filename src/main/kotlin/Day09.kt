class Day09(inputFileName: String, private val preambleLength: Int = 25): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/9

    override fun id() = 9

    override fun part1(): String {
        return inputAsLines.map { it.toLong() }.findInvalidXmas().toString()
    }

    private fun List<Long>.findInvalidXmas(): Long {
        return this.windowed(preambleLength + 1, 1, false)
            .first { !it.isValidXmas() }
            .last()
    }

    private fun List<Long>.isValidXmas():Boolean {
        return this.dropLast(1).asListOfPairs().map { c->c.sum() }.contains(this.last())
    }

    override fun part2(): String {
        val target = inputAsLines.map { it.toLong() }.findInvalidXmas()
        val xmasWeakness = inputAsLines.map { it.toLong() }.getSubListSummingToTarget(target)
        return (xmasWeakness.minOrNull()!! + xmasWeakness.maxOrNull()!!).toString()
    }

    private fun List<Long>.getSubListSummingToTarget(target:Long): List<Long> {
        return this.indices.mapNotNull { rngStart ->
            this.indices.drop(rngStart+1).mapNotNull{rngEnd->
                this.subList(rngStart,rngEnd).takeIf { it.sum()==target }
            }.firstOrNull()
        }.first()
    }


}