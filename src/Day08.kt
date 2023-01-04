import java.lang.Integer.max


fun main() {
    fun part1(input: List<String>): Int {
        var res = 0
        val n = input.size
        val m = input[0].length
        val used = MutableList(n) { MutableList(m) { false } }
        for (i in 0..n - 1) {
            var mx = -1
            for (j in 0..m - 1) {
                if (mx < input[i][j] - '0') {
                    used[i][j] = true
                    mx = input[i][j] - '0'
                }
            }
            mx = -1
            for (j in m - 1 downTo 0) {
                if (mx < input[i][j] - '0') {
                    used[i][j] = true
                    mx = input[i][j] - '0'
                }
            }
        }
        for (j in 0..m - 1) {
            var mx = -1
            for (i in 0..n - 1) {
                if (mx < input[i][j] - '0') {
                    used[i][j] = true
                    mx = input[i][j] - '0'
                }
            }
            mx = -1
            for (i in n - 1 downTo 0) {
                if (mx < input[i][j] - '0') {
                    used[i][j] = true
                    mx = input[i][j] - '0'
                }
            }
        }
        for (i in 0..n - 1) {
            for (j in 0..m - 1) {
                if (used[i][j]) {
                    res++
                }
            }
        }
        return res
    }

    fun part2(input: List<String>): Int {

        var res = 0
        val n = input.size
        val m = input[0].length
        val used = MutableList(n) { MutableList(m) { MutableList(4) { 0 } } }
        for (i in 0..n - 1) {
            for (j in 0..m - 1) {
                var k = 0
                while (true) {
                    val jj = j + ++k
                    if (jj >= m) break
                    used[i][j][0]++
                    if (input[i][j] <= input[i][jj]) break
                }
                k = 0
                while (true) {
                    val jj = j - ++k
                    if (jj < 0) break
                    used[i][j][2]++
                    if (input[i][j] <= input[i][jj]) break
                }
                k = 0
                while (true) {
                    val ii = i + ++k
                    if (ii >= n) break
                    used[i][j][3]++
                    if (input[i][j] <= input[ii][j]) break
                }
                k = 0
                while (true) {
                    val ii = i - ++k
                    if (ii < 0) break
                    used[i][j][1]++
                    if (input[i][j] <= input[ii][j]) break
                }
            }
        }
        for (i in 0..n - 1) {
            for (j in 0..m - 1) {
                res = max(res, used[i][j][0] * used[i][j][1] * used[i][j][2] * used[i][j][3])
            }
        }
        return res
    }


    fun checkTests(input: List<String>) {
        check(part1(input) == 21)
        check(part2(input) == 8)
    }


    val input = readInput("Day08")
    val input_test = readInput("Day08_test")

    checkTests(input_test)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

