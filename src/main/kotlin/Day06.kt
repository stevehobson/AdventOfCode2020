class Day06(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/6
    override fun id() = 6

    override fun part1(): String {
        return inputAsGroupedLines
            .map { it.joinToString().toSet().filter { c -> c in 'a'..'z' }.size }
            .sum()
            .toString()
    }

    override fun part2(): String {
        return inputAsGroupedLines
            .map { it.fold( ('a'..'z').toSet(), {acc,e -> acc.intersect(e.toSet()) } ).size }
            .sum()
            .toString()
    }
}