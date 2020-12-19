import java.lang.IllegalArgumentException

class Day19(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/19

    override fun id() = 19

    class MessageMatcher( rulesStrings: List<String>) {
        abstract class Rule()
        data class LiteralRule(val literal: Char) : Rule()
        data class ReferenceRulePair(val rules: Pair<List<Int>,List<Int>>) : Rule()
        data class ReferenceRule(val rules: List<Int>) : Rule()

        private val rulesLookup = parseRules(rulesStrings)

        private fun parseRules(rulesStrings: List<String>): Map<Int, Rule> {
            val output = mutableMapOf<Int, Rule>()
            for (ruleLine in rulesStrings) {
                val parts = ruleLine.split(':')
                val id = parts.first().toInt()
                val rule = parts.last().trim()
                when {
                    rule.first() == '"' -> output[id] = LiteralRule(rule[1])
                    rule.contains('|') -> {
                        val ruleSplit = rule.split('|')
                        output[id] = ReferenceRulePair(
                            ruleSplit.first().ruleToListOfInt() to
                            ruleSplit.last().ruleToListOfInt()
                        )
                    }
                    else -> output[id] = ReferenceRule(rule.ruleToListOfInt())
                }
            }
            return output
        }

        private fun String.ruleToListOfInt(): List<Int> {
            return this.trim().split(' ').map { it.toInt() }
        }

        private fun String.matchesRules(rulesList: List<Int>): Boolean {

            if (this.isEmpty() || rulesList.isEmpty())
                return this.isEmpty() && rulesList.isEmpty()

            return when (val nextRule = rulesLookup[rulesList.first()]) {
                is LiteralRule ->
                    this.first() == nextRule.literal && this.drop(1).matchesRules(rulesList.drop(1))
                is ReferenceRulePair ->
                    this.matchesRules(nextRule.rules.first.plus(rulesList.drop(1))) ||
                            this.matchesRules(nextRule.rules.second.plus(rulesList.drop(1)))
                is ReferenceRule ->
                    this.matchesRules(nextRule.rules.plus(rulesList.drop(1)))
                else ->
                    throw IllegalArgumentException("Unknown rule: $nextRule")
            }
        }

        fun matchesRules(message: String) = message.matchesRules(listOf(0))
    }

    override fun part1(): String {
        val matcher = MessageMatcher(inputAsGroupedLines.first())
        val messages = inputAsGroupedLines.last()
        return messages.map{matcher.matchesRules(it)}.count { it }.toString()
    }

    override fun part2(): String {
        val rules = inputAsGroupedLines.first()
            .filter { !it.startsWith("8:") && !it.startsWith("11:") }
            .plus("8: 42 | 42 8")
            .plus("11: 42 31 | 42 11 31")
        val matcher = MessageMatcher(rules)
        val messages = inputAsGroupedLines.last()
        return messages.map{matcher.matchesRules(it)}.count { it }.toString()
    }


}