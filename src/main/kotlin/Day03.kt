class Day03(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/3

    override fun id() = 3

    private fun isTree(row: String, col: Int): Boolean {
        return row[col % row.length] == '#'
    }

    private fun nTreesHit(vector: Pair<Int,Int>) : Int {
        var nTrees = 0
        var col = 0
        for (r in inputAsLines.indices step vector.second) {
            val rowData = inputAsLines[r]
            if (isTree(rowData,col))
                nTrees++
            col+=vector.first
        }
        return nTrees
    }

    override fun part1(): String {
        return nTreesHit(3 to 1).toString()
    }

    override fun part2(): String {
        return listOf( 1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
            .map{nTreesHit(it)}.reduce(Int::times).toString()
    }
}