class Day02(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/2

    override fun id() = 2

    data class PasswordAndPolicy(val val1: Int, val val2: Int, val char: Char, val password: String)

    private fun parseLine(line: String): PasswordAndPolicy {
        //regex for lines of the form 2-9 c: ccccccccc
        val passwordRegex = "([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)".toRegex()

        return passwordRegex.matchEntire(line)
            ?.destructured
            ?.let { (min, max, char, pass) ->
                PasswordAndPolicy(min.toInt(), max.toInt(), char.first(), pass)
            }
            ?: throw IllegalArgumentException("Bad input '$line'")
    }

    override fun part1(): String {
        return inputAsLines.count { passwordMeetsRule1(it) }.toString()
    }

    private fun passwordMeetsRule1(line: String): Boolean {
        val p = parseLine(line)
        val charCount = p.password.count { it==p.char }
        return charCount>=p.val1 && charCount<=p.val2
    }

    private fun passwordMeetsRule2(line: String): Boolean {
        val p = parseLine(line)
        return (p.password[p.val1-1]==p.char) xor (p.password[p.val2-1]==p.char)
    }

    override fun part2(): String {
        return inputAsLines.count { passwordMeetsRule2(it) }.toString()
    }
}