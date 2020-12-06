abstract class Day(inputFileName: String) {

    private val inputFile = Day::class.java.getResource(inputFileName)
    protected val inputAsLines by lazy { inputFile.readText().lines() }
    protected val inputAsGroupedLines by lazy {
        inputFile.readText()
            .split(System.lineSeparator()+System.lineSeparator())
            .map { it.lines() }
    }

    abstract fun id() : Int
    abstract  fun part1() : String
    abstract fun part2() : String

    fun printSolution() {
        println("")
        println("Day ${id()} solutions")
        println("Part 1: ${part1()}")
        println("Part 2: ${part2()}")
    }
}