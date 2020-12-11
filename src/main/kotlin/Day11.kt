class Day11(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/11

    override fun id() = 11

    private fun parseInput() : Array<CharArray> {
        return inputAsLines.map { it.toCharArray() }.toTypedArray()
    }

    private val visionVectors = listOf(
        -1 to -1, -1 to 0, -1 to 1, //row above
        0 to -1, 0 to 1, //either side
        1 to -1, 1 to 0, 1 to 1) //row below

    private fun nOccupiedAdjacent(state: Array<CharArray>, x: Int, y: Int): Int {
        return visionVectors
            .map { (dx,dy) -> state.getOrNull(x+dx)?.getOrNull(y+dy) } //contents of neighbours
            .mapNotNull { it=='#' }
            .count { it }
    }

    private fun nOccupiedVisible(state: Array<CharArray>, x: Int, y: Int): Int {
        return visionVectors.mapNotNull { (dx, dy) ->
                generateSequence(x+dx to y+dy){it.first+dx to it.second+dy} //sequence of visible coords
                    .map { state.getOrNull(it.first)?.getOrNull(it.second) } //value at coords
                    .first{ it!='.' } //first non-seat
                    ?.equals('#') // true if occupied
        }.count { it } //count trues
    }

    data class ProcessResult(val grid: Array<CharArray>, val changed: Boolean)

    private fun Array<CharArray>.processRound(occCounter: (Array<CharArray>, Int, Int)->Int, threshold: Int) : ProcessResult {
        var altered = false
        val nextState = map {it.clone()}.toTypedArray()
        nextState.forEachIndexed{ x, row ->
            row.forEachIndexed { y, state ->
                when (state) {
                    'L' -> if (occCounter(this,x,y)==0) { nextState[x][y]='#';altered=true }
                    '#' -> if (occCounter(this,x,y)>=threshold) { nextState[x][y]='L';altered=true}
                }
            }
        }
        return ProcessResult(nextState, altered)
    }

    override fun part1(): String {
        val initial = ProcessResult(parseInput(),true)
        return generateSequence(initial) { it.grid.processRound(this::nOccupiedAdjacent,4) }
            .first { !it.changed }.grid
            .sumBy {it.count {c->c=='#'}}
            .toString()
    }

    override fun part2(): String {
        val initial = ProcessResult(parseInput(),true)
        return generateSequence(initial) { it.grid.processRound(this::nOccupiedVisible,5) }
            .first { !it.changed }.grid
            .sumBy {it.count {c->c=='#'}}
            .toString()
    }
}