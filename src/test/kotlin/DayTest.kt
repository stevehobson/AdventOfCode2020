import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
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
        TestData(Day10("Day10Example2.txt"),"220")
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
        TestData(Day10("Day10Example2.txt"),"19208")
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