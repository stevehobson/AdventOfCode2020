import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory


internal class DayTest {

    private val daysData = listOf(
            TestData(Day01("Day01Example.txt"),"514579", "241861950"),
            TestData(Day02("Day02Example.txt"),"2", "1"),
            TestData(Day03("Day03Example.txt"),"7", "336"))

    @TestFactory
    fun testPart1s() = daysData.map {
         dynamicTest("Testing Day ${it.implementation.id()}: Part 1 gives ${it.part1Example}") {
            assertEquals(it.part1Example, it.implementation.part1())
         }
    }

    @TestFactory
    fun testPart2s() = daysData.map {
        dynamicTest("Testing Day ${it.implementation.id()}: Part 2 gives ${it.part2Example}") {
            assertEquals(it.part2Example, it.implementation.part2())
        }
    }
}

data class TestData(
    val implementation: Day,
    val part1Example: String,
    val part2Example: String
)