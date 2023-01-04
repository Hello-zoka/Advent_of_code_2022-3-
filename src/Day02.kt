import java.io.File


fun main() {
    fun part1(input: List<String>) : Int {
        var res = 0
        for (i in input) {
            val a = i[0] - 'A'
            val b = i[2] - 'X'
            if (b == a) {
                res += 3
            } else if (b > a || (b == 0 && a == 2)) {
                res += 6
            }
            res += b + 1
        }
        return res
    }

    fun part2(input: List<String>) : Int {
        var res = 0
        for (i in input) {
            val a = i[0] - 'A'
            var b = i[2] - 'X'
            if (b == 0) {
                b = (a - 1 + 3) % 3
                res += 0
            } else if (b == 1) {
                b = a
                res += 3
            } else {
                b = (a + 1) % 3
                res += 6
            }
            res += b + 1
        }
        return res
    }

    fun checkTests(input: List<String>) {
        check(part1(input) == 15)
        check(part2(input) == 12)
    }

    val input = readInput("Day02")
    val input_test = readInput("Day02_test")

    checkTests(input_test)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}


