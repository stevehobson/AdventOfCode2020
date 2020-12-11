class Day10(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/10

    override fun id() = 10

    private val input = inputAsLines.map{ it.toLong() }
    private val adapters = input
        .plusElement(0)
        .plusElement(input.maxOrNull()!! + 3)
        .asSequence()
        .sorted()

    override fun part1(): String {
        return adapters
            .zipWithNext()
            .map {it.second-it.first}
            .let { diffs ->
                diffs.count{ it==1L } * diffs.count { it==3L }
            }
            .toString()
    }

    override fun part2(): String {
        val nWaysToReach = mutableMapOf(0L to 1L) //adapter -> number of ways to get there
        adapters.drop(1) //first adapter is already included above
            .forEach { a1 ->
                val possibleConnectors = adapters.filter { a2 -> a2<a1 && (a1-a2)<=3 } //find adapters within 3
                nWaysToReach[a1] = possibleConnectors.map { nWaysToReach.getOrDefault(it,0)}.sum()
            }
        return nWaysToReach[adapters.last()].toString()
    }


}