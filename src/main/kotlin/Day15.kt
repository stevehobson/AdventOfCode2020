class Day15(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/15

    override fun id() = 15

    private fun playGame (initial: List<Int>, nTurns: Int) : Int {
        val history = initial.dropLast(1).mapIndexed{ i, v -> v to i+1 }.toMap().toMutableMap()
        var lastSpoken = initial.last()
        var turn = initial.size+1
        while (turn <= nTurns) {
            val whenLastSpoken = history.getOrDefault(lastSpoken,0)
            val say  = if (whenLastSpoken==0) 0 else turn - whenLastSpoken - 1
            history[lastSpoken] = turn-1
            lastSpoken = say
            turn++
        }
        return lastSpoken
    }

    override fun part1(): String {
        return playGame(inputAsLine.split(',').map { it.toInt() }, 2020).toString()
    }

    override fun part2(): String {
        return playGame(inputAsLine.split(',').map { it.toInt() }, 30000000).toString()
    }
}