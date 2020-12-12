import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue

class Day12(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/12

    override fun id() = 12

    data class Action(val op: Char, val value: Int)
    data class Pos(val x: Int, val y: Int)

    operator fun Pos.plus(other:Pos) = Pos(x + other.x,y + other.y)
    operator fun Pos.times(value:Int) = Pos(x * value,y * value)
    private fun Pos.rotateAroundOrigin(d:Int) =
        when(d) {
            0 -> Pos(x,y)
            90 -> Pos(y, -x)
            180 -> Pos(-x,-y)
            270 -> Pos(-y, x)
            else -> throw IllegalArgumentException("Cannot rotate by $d")
        }
    private fun Pos.move(facing:Int, distance:Int) =
        when(facing) {
            0 -> Pos(x, y + distance)
            90 -> Pos(x + distance, y)
            180 -> Pos(x, y - distance)
            270 -> Pos(x - distance, y)
            else -> throw IllegalArgumentException("Cannot move in direction $facing")
        }

    data class Ship(val pos: Pos, val facing: Int, val waypoint:Pos=Pos(0,0))
    private fun Ship.move(dir:Int, distance:Int) = Ship(pos.move(dir,distance), facing, waypoint)
    private fun Ship.moveWaypoint(dir:Int, distance:Int) = Ship(pos,facing,waypoint.move(dir,distance))
    private fun Ship.rotate(degrees:Int) = Ship(pos, (facing + degrees) % 360, waypoint)
    private fun Ship.rotateWaypoint(degrees:Int) = Ship(pos, facing, waypoint.rotateAroundOrigin(degrees))
    private fun Ship.moveForward(distance:Int) = move(facing,distance)
    private fun Ship.moveToWaypoint(distance:Int) = Ship(pos+(waypoint*distance),facing,waypoint)

    private fun parseLine(line: String) = Action(line.first(), line.drop(1).toInt())

    override fun part1(): String {
        return inputAsLines.map(this::parseLine).fold(Ship(Pos(0,0),90), { state, action ->
            when(action.op) {
                'N' -> state.move(0,action.value)
                'S' -> state.move(180,action.value)
                'E' -> state.move(90,action.value)
                'W' -> state.move(270,action.value)
                'L' -> state.rotate(360-action.value)
                'R' -> state.rotate(action.value)
                'F' -> state.moveForward(action.value)
                else -> throw IllegalArgumentException("Unknown action ${action.op}")
            }
        }).let {
            (it.pos.x.absoluteValue + it.pos.y.absoluteValue).toString()
        }
    }

    override fun part2(): String {
        return inputAsLines.map(this::parseLine)
            .fold(Ship(Pos(0,0),90, Pos(10,1)), { state, action ->
            when(action.op) {
                'N' -> state.moveWaypoint(0,action.value)
                'S' -> state.moveWaypoint(180,action.value)
                'E' -> state.moveWaypoint(90,action.value)
                'W' -> state.moveWaypoint(270,action.value)
                'L' -> state.rotateWaypoint(360 - action.value)
                'R' -> state.rotateWaypoint(action.value)
                'F' -> state.moveToWaypoint(action.value)
                else -> throw IllegalArgumentException("Unknown action ${action.op}")
            }
        }).let {
            (it.pos.x.absoluteValue + it.pos.y.absoluteValue).toString()
        }
    }
}