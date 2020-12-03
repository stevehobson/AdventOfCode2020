class Day03: IDay {
    //Solution to https://adventofcode.com/2020/day/3
    override fun id(): Int {
        return 3
    }

    private fun isTree(row: String, col: Int): Boolean {
        return row[col % row.length] == '#'
    }

    private fun nTreesHit(input: List<String>, xTrajectory: Int, yTrajectory: Int) : Int {
        var nTrees = 0
        var col = 0
        for (r in input.indices step yTrajectory) {
            val rowData = input[r]
            if (isTree(rowData,col))
                nTrees++
            col+=xTrajectory
        }
        return nTrees
    }

    override fun part1(input: List<String>): String {
        return nTreesHit(input,3,1).toString()
    }

    override fun part2(input: List<String>): String {
        val total =  nTreesHit(input,1,1) *
                nTreesHit(input,3,1) *
                nTreesHit(input,5,1) *
                nTreesHit(input,7,1) *
                nTreesHit(input,1,2)
        return total.toString()
    }
}