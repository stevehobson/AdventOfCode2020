class Day14(inputFileName: String): Day(inputFileName) {
    //Solution to https://adventofcode.com/2020/day/14

    override fun id() = 14

    abstract class Instruction()
    data class WriteInstruction(val address: Long, val value: Long) : Instruction()
    data class MaskInstruction(val mask: String) : Instruction()

    private fun parseInput(): List<Instruction> {
        return inputAsLines.map { line ->
            val splitLine = line.split(" = ")
            when(splitLine.first().substring(0..2)) {
                "mas" -> MaskInstruction(splitLine.last())
                "mem" -> WriteInstruction(parseMem(splitLine.first()), splitLine.last().toLong())
                else -> throw IllegalArgumentException("Unknown instruction: $line")
            }
        }
    }

    private fun parseMem(instruction: String) : Long {
        return instruction.split('[').last()
            .dropLast(1) //drop ]
            .toLong()
    }

    class Memory(private val nBits: Int = 36) {

        private val storage = mutableMapOf<Long,Long>()

        private var mask = "".padStart(nBits,'x')

        private fun Long.applyMaskToVal(): Long {
            return this.toString(2)
                .padStart(nBits,'0')
                .zip(mask)
                .map { (v,m)->
                    when(m) {
                        'X' -> v
                        else -> m
                    }
                }.joinToString("").toLong(2)
        }

        private fun Long.applyMaskToAddress(): List<Long> {
            return this.toString(2)
                .padStart(nBits,'0')
                .zip(mask)
                .fold(listOf("")) { addrs, (a,m) ->
                    when(m) {
                        '1' -> addrs.map{it.plus('1')}
                        '0' -> addrs.map{it.plus(a)}
                        'X' -> addrs.flatMap {listOf(it.plus('1'),it.plus('0'))}
                        else -> throw IllegalArgumentException("Unknown mask bit: $m")
                    }
                }.map{it.toLong(2)}
        }

        fun setMask(mask: String) {this.mask = mask}

        fun writeV1(instruction: WriteInstruction) {
            storage[instruction.address] = instruction.value.applyMaskToVal()
        }

        fun writeV2(instruction: WriteInstruction) {
            instruction.address.applyMaskToAddress().forEach {
                storage[it] = instruction.value
            }
        }

        fun getTotal() = storage.values.sum()
    }

    override fun part1(): String {
        val memory = Memory()
        parseInput().forEach {
            if (it is WriteInstruction) {
                memory.writeV1(it)
            } else if (it is MaskInstruction) {
                memory.setMask(it.mask)
            }
        }
        return memory.getTotal().toString()
    }

    override fun part2(): String {
        val memory = Memory()
        parseInput().forEach {
            if (it is WriteInstruction) {
                memory.writeV2(it)
            } else if (it is MaskInstruction) {
                memory.setMask(it.mask)
            }
        }
        return memory.getTotal().toString()
    }
}