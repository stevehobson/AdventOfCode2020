class Day02: IDay {
    //Solution to https://adventofcode.com/2020/day/2

    data class PasswordAndPolicy(val val1: Int, val val2: Int, val char: Char, val password: String)

    override fun id(): Int {
        return 2
    }

    private fun parseLine(line: String): PasswordAndPolicy {
        //regex for lines of the form 2-9 c: ccccccccc
        val passwordRegex = "([0-9]*)-([0-9]*) ([a-z]): ([a-z]*)".toRegex()

        return passwordRegex.matchEntire(line)
            ?.destructured
            ?.let { (min, max, char, pass) ->
                PasswordAndPolicy(min.toInt(), max.toInt(), char.first(), pass)
            }
            ?: throw IllegalArgumentException("Bad input '$line'")
    }

    override fun part1(input: List<String>): String {
        var count = 0
        for (line in input){
            val p = parseLine(line)
            val charCount = p.password.count { it==p.char }
            if (charCount>=p.val1 && charCount<=p.val2)
                count++
        }
        return count.toString()
    }

    override fun part2(input: List<String>): String {
        var count = 0
        for (line in input){
            val p = parseLine(line)
            if ((p.password[p.val1-1]==p.char) xor (p.password[p.val2-1]==p.char))
                count++
        }
        return count.toString()
    }
}