class DayRunner(private val day: IDay, private val inputFileName: String) {
    fun printSolution() {
        val input = InputReader(inputFileName).getInput()
        println("")
        println("Day ${day.id()} solutions")
        println("Part 1: ${day.part1(input)}")
        println("Part 2: ${day.part2(input)}")
    }
}