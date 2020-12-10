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

    //List extension functions
    //returns list of all combinations of pairs of items in the list
    protected fun <T> List<T>.asListOfPairs(): List<List<T>> {
        val output = mutableListOf<List<T>>()
        for (i in this.indices) {
            for (j in i + 1 until this.size) {
                output.add(listOf(this[i],this[j]))
            }
        }
        return output
    }

    //returns list of all combinations of triples of items in the list
    protected fun <T> List<T>.asListOfTriples(): List<List<T>> {
        val output = mutableListOf<List<T>>()
        for (i in this.indices) {
            for (j in i + 1 until this.size) {
                for (k in j + 1 until this.size) {
                    output.add(listOf(this[i],this[j],this[k]))
                }
            }
        }
        return output
    }
}