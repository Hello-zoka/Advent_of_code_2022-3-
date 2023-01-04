
fun main() {
    fun part1(input: List<String>): Int {
        val cnt = MutableList(27){0}
        for (i in 0..input[0].length) {
            val cur_char = input[0][i]
            cnt[cur_char - 'a']++
            if (i >= 4) {
                cnt[input[0][i - 4] - 'a']--
            }
            if (i >= 3) {
                var bl = true
                for (j in cnt) {
                    if (j > 1) {
                        bl = false
                        break
                    }
                }
                if (bl) {
                    return i + 1
                }
            }
        }
        return -1
    }

    fun part2(input: List<String>): Int {
        val cnt = MutableList(27){0}
        for (i in 0..input[0].length) {
            val cur_char = input[0][i]
            cnt[cur_char - 'a']++
            if (i >= 14) {
                cnt[input[0][i - 14] - 'a']--
            }
            if (i >= 13) {
                var bl = true
                for (j in cnt) {
                    if (j > 1) {
                        bl = false
                        break
                    }
                }
                if (bl) {
                   return i + 1
                }
            }
        }
        return -1
    }


    fun checkTests(input: List<String>) { // There are more than one test, use your hands)
        check(part1(input) == 10)
        check(part2(input) == 29)
    }


    val input = readInput("Day06")
    val input_test = readInput("Day06_test")

    checkTests(input_test)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

