class Day18(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/18

    override fun id() = 18

    class Evaluator(private val precedence: List<Set<Char>>) {

        abstract class Expression() {
            abstract fun evaluate(): Long
        }

        data class ValExpression(val num: Long) : Expression() {
            override fun evaluate(): Long {
                return num
            }
        }

        data class OpExpression(val op: Char, val lhs: Expression, val rhs: Expression) : Expression() {
            override fun evaluate(): Long {
                return when (op) {
                    '+' -> lhs.evaluate() + rhs.evaluate()
                    '*' -> lhs.evaluate() * rhs.evaluate()
                    else -> throw IllegalArgumentException("Unsupported operation: $op")
                }
            }
        }

        //find last char at not inside parentheses
        private fun String.lastOutside(char: Char): Int {
            var nOpen = 0
            for (i in this.indices.reversed()) {
                when (this[i]) {
                    '(' -> nOpen++
                    ')' -> nOpen--
                }
                if (this[i] == char && nOpen == 0) return i
            }
            return -1
        }

        private fun String.lastOpen() = this.lastOutside('(')

        private fun String.toExpression(): Expression {
            //if matching parentheses at outside, evaluate inside
            if (this.indexOfLast { it == ')' } == this.lastIndex && this.lastOpen() == 0) {
                return this.substring(1, this.lastIndex).toExpression()
            }

            //find lowest precedence op
            val op = precedence.reversed().map { chars ->
                chars.map { this.lastOutside(it) }.maxOrNull()
            }.firstOrNull() { it != -1 }

            //if no operator, this is a value
            return if (op == null)
                ValExpression(this.toLong())
            else //otherwise, split on op and evaluate lhs/rhs
                OpExpression(
                    this[op],
                    this.substring(0, op).toExpression(),
                    this.substring(op + 1..this.lastIndex).toExpression()
                )
        }

        fun evaluate(math: String)  = math.toExpression().evaluate()

    }

    override fun part1(): String {
        val evaluator = Evaluator(listOf(setOf('+','*')))
        return inputAsLines.map{it.filterNot { c->c==' '}}
            .map {evaluator.evaluate(it)}
            .sum().toString()
    }

    override fun part2(): String {
        val evaluator = Evaluator(listOf(setOf('+'),setOf('*')))
        return inputAsLines.map{it.filterNot { c->c==' '}}
            .map {evaluator.evaluate(it)}
            .sum().toString()
    }
}