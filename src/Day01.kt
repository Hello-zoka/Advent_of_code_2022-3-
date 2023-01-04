import java.io.File

fun List<String>.splitInput(elem: String): List<List<String>> {
    if (isEmpty()) {
        return emptyList()
    }
    val transformed: List<MutableList<String>> = buildList {
        add(mutableListOf())
        this@splitInput.forEach {
            when (it) {
                elem -> add(mutableListOf())
                else -> last().add(it)
            }
        }
    }
    return transformed
}

fun main() {
    fun part1(input: List<Int>) = input.max()

    fun part2(input: List<Int>) = input.sorted().takeLast(3).sum()


    fun checkTests(input: List<Int>) {
        check(part1(input) == 24000)
        check(part2(input) == 206104)
    }

    val input: List<Int> = File("tests", "Day01.txt").readLines().splitInput("").map { it.sumOf { it.toInt() } }
    val input_test = File("tests", "Day01_test.txt").readLines().splitInput("").map { it.sumOf { it.toInt() } }

    checkTests(input_test)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
