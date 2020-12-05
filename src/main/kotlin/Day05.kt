class Day05(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/5
    override fun id() = 5

    private fun strToSeatId(s: String): Int {
        return s.replace('B', '1')
            .replace('R', '1')
            .replace('F', '0')
            .replace('L', '0')
            .toInt(2)
    }

    private fun getSeatIds(): List<Int> {
        return input.map{ strToSeatId(it) }
    }

    override fun part1(): String {
        return getSeatIds()
            .maxOrNull()
            .toString()
    }

    override fun part2(): String {
        return getSeatIds()
            .asSequence()
            .sorted()
            .zipWithNext()
            .filter{ (x,y) -> y-x==2 }
            .map{ (x,_) -> x+1 }
            .firstOrNull()
            .toString()
    }

}