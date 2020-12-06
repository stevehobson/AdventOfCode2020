import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class DayTest {

    @Test
    fun day1_part1_example() {
        assertEquals("514579", Day01("Day01Example.txt").part1())
    }

    @Test
    fun day1_part2_example() {
        assertEquals("241861950", Day01("Day01Example.txt").part2())
    }

    @Test
    fun day2_part1_example() {
        assertEquals("2", Day02("Day02Example.txt").part1())
    }

    @Test
    fun day2_part2_example() {
        assertEquals("1", Day02("Day02Example.txt").part2())
    }

    @Test
    fun day3_part1_example() {
        assertEquals("7", Day03("Day03Example.txt").part1())
    }

    @Test
    fun day3_part2_example() {
        assertEquals("336", Day03("Day03Example.txt").part2())
    }

    @Test
    fun day4_part1_example() {
        assertEquals("2", Day04("Day04Example.txt").part1())
    }

    @Test
    fun day4_part2_allInvalid() {
        assertEquals("0", Day04("Day04AllInvalid.txt").part2())
    }

    @Test
    fun day4_part2_allValid() {
        assertEquals("4", Day04("Day04AllValid.txt").part2())
    }

    @Test
    fun day5_part1_example() {
        assertEquals("820", Day05("Day05Example.txt").part1())
    }

    @Test
    fun day6_part1_example() {
        assertEquals("11", Day06("Day06Example.txt").part1())
    }

    @Test
    fun day6_part2_example() {
        assertEquals("6", Day06("Day06Example.txt").part2())
    }

}