import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day01Test {

    private val input = InputReader("Day01Example.txt").getInput()

    @BeforeEach
    fun setUp() {

    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun part1_example() {
        val day01 = Day01()

        val result = day01.part1(input)

        assertEquals("514579", result)
    }

    @Test
    fun part2_example() {
        val day01 = Day01()

        val result = day01.part2(input)

        assertEquals("241861950", result)
    }
}