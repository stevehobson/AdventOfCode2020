import kotlin.math.abs

class Day17(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/17

    override fun id() = 17

    private val initialCubes = inputAsLines
        .mapIndexed{i,str -> str.mapIndexedNotNull { j, v -> if (v=='#') Coord(i,j,0,0) else null }}
        .flatten()
        .toSet()

    data class Coord(val x: Int, val y: Int, val z: Int, val w: Int)

    class PocketDimension(initialCubes: Set<Coord>, private val is4D: Boolean) {

        private var cubeStates : Set<Coord> = initialCubes

        private fun Set<Coord>.isActive(c: Coord) = this.contains(c)
        private fun Set<Coord>.countNeighboursOf(c: Coord) = this.count { it.isNeighbourOf(c) }
        private fun Set<Coord>.xRange() = this.fold(0..0){r,c-> minOf(r.first,c.x)..maxOf(r.last,c.x)}.pad()
        private fun Set<Coord>.yRange() = this.fold(0..0){r,c-> minOf(r.first,c.y)..maxOf(r.last,c.y)}.pad()
        private fun Set<Coord>.zRange() = this.fold(0..0){r,c-> minOf(r.first,c.z)..maxOf(r.last,c.z)}.pad()
        private fun Set<Coord>.wRange() = this.fold(0..0){r,c-> minOf(r.first,c.w)..maxOf(r.last,c.w)}.pad()

        private fun IntRange.pad() = this.first-1..this.last+1


        private operator fun Coord.plus(other:Coord) =
            Coord(this.x+other.x, this.y+other.y, this.z+other.z, this.w+other.w)

        private fun Coord.isNeighbourOf(other:Coord) = this!=other &&
                                                        abs(this.x-other.x)<=1 &&
                                                        abs(this.y-other.y)<=1 &&
                                                        abs(this.z-other.z)<=1 &&
                                                        abs(this.w-other.w)<=1

        fun runStep() {
            val newState = mutableSetOf<Coord>()
            for (x in cubeStates.xRange())
                for (y in cubeStates.yRange())
                    for (z in cubeStates.zRange())
                        for (w in (if (is4D) cubeStates.wRange() else 0..0)) {
                            val c = Coord(x, y, z, w)
                            val nNeighbours = cubeStates.countNeighboursOf(c)
                            if ((nNeighbours == 3) or (cubeStates.isActive(c) && nNeighbours==2))
                                newState.add(c)
                        }
            cubeStates = newState
        }

        fun countCubes() : Int = cubeStates.size

    }


    override fun part1(): String {
        val dim = PocketDimension(initialCubes, false)

        for(i in 1..6)
            dim.runStep()

        return dim.countCubes().toString()
    }

    override fun part2(): String {
        val dim = PocketDimension(initialCubes, true)

        for(i in 1..6)
            dim.runStep()

        return dim.countCubes().toString()
    }

}
