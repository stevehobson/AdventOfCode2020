import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory


internal class DayTest {

    data class TestData(
        val implementation: Day,
        val expectedResult: String
    )

    private val part1Tests = listOf(
        TestData(Day01("Day01Example.txt"),"514579"),
        TestData(Day02("Day02Example.txt"),"2"),
        TestData(Day03("Day03Example.txt"),"7"),
        TestData(Day04("Day04Example.txt"),"2"),
        TestData(Day05("Day05Example.txt"),"820"),
        TestData(Day06("Day06Example.txt"),"11"),
        TestData(Day07("Day07Example.txt"),"4"),
        TestData(Day08("Day08Example.txt"),"5"),
        TestData(Day09("Day09Example.txt",5),"127"),
        TestData(Day10("Day10Example.txt"),"35"),
        TestData(Day10("Day10Example2.txt"),"220"),
        TestData(Day11("Day11Example.txt"),"37"),
        TestData(Day12("Day12Example.txt"), "25"),
        TestData(Day13("Day13Example.txt"), "295"),
        TestData(Day14("Day14Example.txt"), "165"),
        TestData(Day15("Day15Example.txt"), "436"),
        TestData(Day16("Day16Example.txt"), "71"),
        TestData(Day17("Day17Example.txt"), "112"),
        TestData(Day18("Day18Example.txt"), "26457"),
        TestData(Day19("Day19Example.txt"), "2")
    )

    private val part2Tests = listOf(
        TestData(Day01("Day01Example.txt"),"241861950"),
        TestData(Day02("Day02Example.txt"),"1"),
        TestData(Day03("Day03Example.txt"),"336"),
        TestData(Day04("Day04AllInvalid.txt"),"0"),
        TestData(Day04("Day04AllValid.txt"),"4"),
        TestData(Day06("Day06Example.txt"),"6"),
        TestData(Day07("Day07Example.txt"),"32"),
        TestData(Day07("Day07Example2.txt"),"126"),
        TestData(Day08("Day08Example.txt"),"8"),
        TestData(Day09("Day09Example.txt",5),"62"),
        TestData(Day10("Day10Example.txt"),"8"),
        TestData(Day10("Day10Example2.txt"),"19208"),
        TestData(Day11("Day11Example.txt"),"26"),
        TestData(Day12("Day12Example.txt"), "286"),
        TestData(Day13("Day13Example.txt"), "1068781"),
        TestData(Day13("Day13Example2.txt"), "3417"),
        TestData(Day13("Day13Example3.txt"), "754018"),
        TestData(Day13("Day13Example4.txt"), "779210"),
        TestData(Day13("Day13Example5.txt"), "1261476"),
        TestData(Day13("Day13Example6.txt"), "1202161486"),
        TestData(Day14("Day14Example2.txt"), "208"),
        TestData(Day16("Day16Example2.txt"), "143"),
        TestData(Day17("Day17Example.txt"), "848"),
        TestData(Day18("Day18Example.txt"), "694173"),
        TestData(Day19("Day19Example2.txt"), "12")
    )

    @TestFactory
    fun testPart1s() = part1Tests.map {
        DynamicTest.dynamicTest(
            "Testing Day ${it.implementation.id()} Part 1 on ${it.implementation.getInputName()}") {
            assertEquals(it.expectedResult, it.implementation.part1())
        }
    }

    @TestFactory
    fun testPart2s() = part2Tests.map {
        DynamicTest.dynamicTest(
            "Testing Day ${it.implementation.id()} Part 2 on ${it.implementation.getInputName()}") {
            assertEquals(it.expectedResult, it.implementation.part2())
        }
    }

}