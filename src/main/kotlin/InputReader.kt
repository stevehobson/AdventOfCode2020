class InputReader(private val inputFileName: String) {
    fun getInput() : List<String> {
        return InputReader::class.java.getResource(inputFileName).readText().lines()
    }
}