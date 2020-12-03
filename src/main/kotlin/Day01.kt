class Day01(inputFileName: String) : Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/1
    private val magicNumber = 2020

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

    override fun id(): Int {
        return 1
    }

    override fun part1() : String {
        val pairs = generatePairs(input.map{it.toInt()})
        return findMagicProduct(pairs).toString()
    }

    override fun part2() : String {
        val triples = generateTriples(input.map{it.toInt()})
        return findMagicProduct(triples).toString()
    }

}