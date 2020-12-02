import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day02Test {

    private val input = InputReader("Day02Example.txt").getInput()

    @Test
    fun part1_example() {
        val day02 = Day02()

        val result = day02.part1(input)

        assertEquals("2", result)
    }

    @Test
    fun part2_example() {
        val day02 = Day02()

        val result = day02.part2(input)

        assertEquals("1", result)
    }
}