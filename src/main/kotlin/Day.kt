abstract class Day(inputFileName: String) {

    protected val input =  Day::class.java.getResource(inputFileName).readText().lines()

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