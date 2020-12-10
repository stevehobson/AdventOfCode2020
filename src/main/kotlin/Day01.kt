class Day01(inputFileName: String) : Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/1

    override fun id() = 1

    private val magicNumber = 2020

    private fun findMagicProduct(tuples: List<List<Int>>) : Int {
        for (p in tuples) {
            if (p.sum() == magicNumber) {
                return p.reduce(Int::times) //returns product of list
            }
        }
        return 0
    }

    override fun part1() : String {
        val pairs = inputAsLines.map{it.toInt()}.asListOfPairs()
        return findMagicProduct(pairs).toString()
    }

    override fun part2() : String {
        val triples = inputAsLines.map{it.toInt()}.asListOfTriples()
        return findMagicProduct(triples).toString()
    }

}