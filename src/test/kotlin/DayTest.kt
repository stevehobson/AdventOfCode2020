import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory


internal class DayTest {

    private val daysData = listOf(
            TestData(Day01(), "Day01Example.txt", "514579", "241861950"),
            TestData(Day02(), "Day02Example.txt", "2", "1"))

    @TestFactory
    fun testPart1s() = daysData.map {
         dynamicTest("Testing ${it.inputFileName} part 1 gives ${it.part1Example}") {
            val input = InputReader(it.inputFileName).getInput()
            assertEquals(it.part1Example, it.implementation.part1(input))
         }
    }

    @TestFactory
    fun testPart2s() = daysData.map {
        dynamicTest("Testing ${it.inputFileName} part 2 gives ${it.part2Example}") {
            val input = InputReader(it.inputFileName).getInput()
            assertEquals(it.part2Example, it.implementation.part2(input))
        }
    }
}

data class TestData(
    val implementation: IDay,
    val inputFileName: String,
    val part1Example: String,
    val part2Example: String
)