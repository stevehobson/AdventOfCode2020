class Day07(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/7

    override fun id() = 7

    data class BagCount(val colour: String, val count: Int)

    private val bagRules by lazy { parseRules() }

    private fun parseRules(): Map<String,List<BagCount>>{
        val output = mutableMapOf<String,List<BagCount>>()
        for (line in inputAsLines) {
            val pivot = " bags contain "
            val container = line.substringBefore(pivot)
            val contents = line.substringAfter(pivot)
            output[container]  = contents.split(", ").mapNotNull { parseNumberOfBags(it) }
        }
        return output
    }

    private fun parseNumberOfBags(content: String) : BagCount? {
        val containsRegex =  "([0-9]+) ([a-z_ ]+) bag.*".toRegex()
        return containsRegex.matchEntire(content)?.destructured?. let {
                (count, bagType) -> BagCount(bagType, count.toInt())
        }
    }

    override fun part1(): String {
        return bagRules.keys.map{bagContainsTarget("shiny gold",it)}.count{ it }.toString()
    }

    private fun bagContainsTarget(target: String, container: String): Boolean {
        val containedBags = bagRules.getValue(container).map { it.colour }
        return containedBags.contains(target) ||
                containedBags.map { c ->bagContainsTarget(target,c)}.reduceOrNull(Boolean::or) ?: false
    }

    override fun part2(): String {
        return nBagsInside("shiny gold").toString()
    }

    private fun nBagsInside(container: String): Int {
        return bagRules.getValue(container)
            .map { it.count * (1 + nBagsInside(it.colour)) }
            .sum()
    }
}