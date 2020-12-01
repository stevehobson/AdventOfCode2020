class Day01 {
    //Solution to https://adventofcode.com/2020/day/1

    private val input1 = Day01::class.java.getResource("Day01.txt").readText().split("\n")
    private val magicNumber = 2020

    fun printSolution() {
        println("Part 1 solution: ${part1(input1)}")
        println("Part 2 solution: ${part2(input1)}")
    }

    fun part1(input : List<String>) : String {
        val numericInput = input.map { it.trim().toInt() }

        for (i in 0 until numericInput.size-1) {
            for (j in i+1 until numericInput.size-1) {
                val a = numericInput[i]
                val b = numericInput[j]
                if ((a + b) == magicNumber) {
                    return (a * b).toString()
                }
            }
        }

        return ""
    }

    fun part2(input : List<String>) : String {
        val numericInput = input.map { it.trim().toInt() }

        for (i in 0 until numericInput.size-1) {
            for (j in i+1 until numericInput.size-1) {
                for (k in j+1 until numericInput.size-1) {
                    val a = numericInput[i]
                    val b = numericInput[j]
                    val c = numericInput[k]

                    if ((a + b + c) == magicNumber) {
                        return (a * b * c).toString()
                    }
                }
            }
        }

        return ""
    }

}