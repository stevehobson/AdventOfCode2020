class Day16(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/16

    override fun id() = 16

    private val rules: Map<String,Set<IntRange>>
    private val myTicket: List<Int>
    private val otherTickets: List<List<Int>>

    init {
        val groups = inputAsGroupedLines
        rules = parseFields(groups.first())
        myTicket = groups[1][1].split(",").map { it.toInt() }
        otherTickets = groups.last().drop(1).map{ nums -> nums.split(",").map { it.toInt()} }
    }

    private fun parseFields(lines: List<String>): Map<String,Set<IntRange>> {
        return lines.map {
            val parsed = it.split(':')
            parsed.first() to parseRules(parsed.last().trim())
        }.toMap()
    }

    private fun parseRules(line: String): Set<IntRange> {
        return line.split(" or ")
            .map {it.split('-')}
            .map {it.first().toInt()..it.last().toInt()}
            .toSet()
    }

    private fun Int.obeysAnyRule(): Boolean {
        val allRules = rules.values.reduce(Set<IntRange>::union)
        return this isInOneOf allRules
    }

    private infix fun Int.isInOneOf(ranges: Set<IntRange>): Boolean {
        return ranges.map { this in it }.any { it }
    }

    override fun part1(): String {
        return otherTickets.flatten().filter { !it.obeysAnyRule() }.sum().toString()
    }

    override fun part2(): String {

        val validOtherTickets = otherTickets.filter { ticket -> ticket.all { v -> v.obeysAnyRule()} }
        val allTickets = validOtherTickets.plusElement(myTicket)

        val fieldValues = rules.keys.indices.map { i-> allTickets.map {it[i]} }

        //build map of indices -> labels whose rules they obey
        val possibleAssignments = mutableMapOf<Int,Set<String>>()
        for (i in fieldValues.indices) {
            val validFields = rules.mapNotNull { rule ->
                if (fieldValues[i].map {it isInOneOf rule.value}.all { it })
                    rule.key
                else null
            }.toSet()
            possibleAssignments[i] = validFields
        }

        while(possibleAssignments.values.any{ it.size>1 }) {
            val certainAssignments = possibleAssignments.values.filter { it.size==1 }.reduce(Set<String>::union)
            possibleAssignments.forEach { (i, fields) ->
                if (fields.size>1)
                    possibleAssignments[i] = fields - certainAssignments
            }
        }

        val departureIndices = possibleAssignments.filter { it.value.first().startsWith("departure") }.keys

        return departureIndices.map { myTicket[it].toLong()}.reduce(Long::times).toString()
    }
}