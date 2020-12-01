class Day01 {
    //Solution to https://adventofcode.com/2020/day/1
    private val magicNumber = 2020

    fun printSolution() {
        val input = InputReader("Day01.txt").getInput().map{it.toInt()}
        println("Part 1 solution: ${part1(input)}")
        println("Part 2 solution: ${part2(input)}")
    }

    private fun generatePairs(input: List<Int>): List<List<Int>> {
        val output = mutableListOf<List<Int>>()
        for (i in input.indices) {
            for (j in i + 1 until input.size) {
                output.add(listOf(input[i],input[j]))
            }
        }
        return output
    }

    private fun generateTriples(input: List<Int>): List<List<Int>> {
        val output = mutableListOf<List<Int>>()
        for (i in input.indices) {
            for (j in i + 1 until input.size) {
                for (k in j + 1 until input.size) {
                    output.add(listOf(input[i],input[j],input[k]))
                }
            }
        }
        return output
    }

    private fun findMagicProduct(tuples: List<List<Int>>) : Int {
        for (p in tuples) {
            if (p.sum() == magicNumber) {
                return p.reduce(Int::times) //returns product of list
            }
        }
        return 0
    }

    fun part1(input : List<Int>) : String {
        val pairs = generatePairs(input)
        return findMagicProduct(pairs).toString()
    }

    fun part2(input : List<Int>) : String {
        val triples = generateTriples(input)
        return findMagicProduct(triples).toString()
    }

}