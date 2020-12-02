class DayRunner(private val day: IDay, private val inputFileName: String) {
    fun printSolution() {
        val input = InputReader(inputFileName).getInput()
        println("Part 1 solution: ${day.part1(input)}")
        println("Part 2 solution: ${day.part2(input)}")
    }
}