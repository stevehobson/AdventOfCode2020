class Day08(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/8

    override fun id() = 8

    data class Instruction(val op: String, val arg: Int)
    data class ProgramResult(val output: Int, val hitLoop: Boolean)

    private val instructions = inputAsLines.map { parseLine(it) }.toTypedArray()

    private fun parseLine(line: String): Instruction{
        val splitLine = line.split(" ")
        return Instruction(splitLine.first(),splitLine.last().toInt())
    }

    override fun part1(): String {
        return runProgram(instructions).output.toString()
    }

    private fun runProgram(program: Array<Instruction>) : ProgramResult {
        var acc = 0
        var pointer = 0
        var hitLoop = false
        val visited =  mutableSetOf<Int>()
        while(pointer in program.indices && !hitLoop) {
            val instruction = program[pointer]
            visited.add(pointer)
            when(instruction.op) {
                "jmp" -> pointer+=instruction.arg
                "acc" -> {acc+=instruction.arg; pointer++}
                else -> pointer++ //noop
            }
            hitLoop = pointer in visited
        }
        return ProgramResult(acc,hitLoop)
    }

    override fun part2(): String {
        for(i in instructions.indices) {
            if (instructions[i].op != "acc") {
                val tmpInstructions = instructions.mapIndexed {
                        idx, inst -> if (i == idx) toggleInstruction(inst) else inst.copy()
                }
                val result = runProgram(tmpInstructions.toTypedArray())
                if (!result.hitLoop)
                    return result.output.toString()
            }
        }
        return ""
    }

    private fun toggleInstruction(instruction: Instruction): Instruction{
        return when (instruction.op) {
            "jmp" -> Instruction("nop", instruction.arg)
            "nop" -> Instruction("jmp", instruction.arg)
            else-> instruction
        }
    }
}