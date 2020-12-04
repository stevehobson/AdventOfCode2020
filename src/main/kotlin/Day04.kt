class Day04(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/4

    private val passports = getPassports()

    private fun getPassports(): List<HashMap<String, String>> {
        val passports = mutableListOf(HashMap<String, String>())

        for (line in input) {
            if (line.isEmpty()) {
                passports.add(HashMap())
            } else {
                passports.last().putAll(parseLine(line))
            }
        }
        return passports
    }

    private fun parseLine(line: String): Map<String, String> {
        return line.split(" ")
            .map {it.split(":")}
            .map{it.first() to it.last()}
            .toMap()
    }

    override fun id(): Int {
        return 4
    }

    override fun part1(): String {
        return passports.filter{isValidPart1(it)}.size.toString()
    }

    private fun isValidPart1(passport: HashMap<String, String>): Boolean {
        val mandatoryFields = setOf("byr","iyr","eyr","hgt","hcl","ecl","pid")
        return passport.keys.containsAll(mandatoryFields)
    }

    override fun part2(): String {
        return passports.filter{isValidPart1(it)}.filter{isValidPart2(it)}.size.toString()
    }

    private fun isValidPart2(passport: HashMap<String, String>): Boolean {
                // byr (Birth Year) - four digits; at least 1920 and at most 2002.
        return passport["byr"]?.toInt() in 1920..2002 &&
                // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
                passport["iyr"]?.toInt() in 2010..2020 &&
                // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
                passport["eyr"]?.toInt() in 2020..2030 &&
                // hgt (Height) - a number followed by either cm or in:
                //     If cm, the number must be at least 150 and at most 193.
                //     If in, the number must be at least 59 and at most 76.
                (passport["hgt"]?:"").let {
                    ("[0-9]{3}cm".toRegex().matches(it) && it.take(3).toInt() in 150..193) ||
                    ("[0-9]{2}in".toRegex().matches(it) && it.take(2).toInt() in 59..76)
                } &&
                // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
                "#[0-9,a-f]{6}".toRegex().matches(passport["hcl"]?:"") &&
                // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
                "amb|blu|brn|gry|grn|hzl|oth".toRegex().matches(passport["ecl"]?:"") &&
                // pid (Passport ID) - a nine-digit number, including leading zeroes.
                "[0-9]{9}".toRegex().matches(passport["pid"]?:"")
                // cid (Country ID) - ignored, missing or not.
    }
}