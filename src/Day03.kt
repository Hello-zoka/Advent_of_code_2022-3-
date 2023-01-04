import java.io.File

fun main() {
    fun part1(input: List<String>): Int {
        var res = 0
        for (i in input) {
            val cnt = MutableList(53) { 0 }
            for (ind in 0..(i.length / 2) - 1) {
                var x : Int
                if (i[ind].isLowerCase()) {
                    x = i[ind] - 'a' + 1;
                } else {
                    x = i[ind] - 'A' + 27;
                }
                cnt[x]++
            }
            for (ind in (i.length / 2)..i.length - 1) {
                var x : Int
                if (i[ind].isLowerCase()) {
                    x = i[ind] - 'a' + 1;
                } else {
                    x = i[ind] - 'A' + 27;
                }
                if (cnt[x] != 0) {
                    res += x
                    cnt[x] = 0
                }
            }
        }
        return res
    }

    fun part2(input: List<String>): Int {
        var res = 0
        var i = 0
        while (i < input.size) {
            val cnt = MutableList(3) { MutableList(55) { 0 } }
            for (pos in 0..2) {
                val s = input[i + pos]
                for (ind in 0..s.length - 1) {
                    var x: Int
                    if (s[ind].isLowerCase()) {
                        x = s[ind] - 'a' + 1;
                    } else {
                        x = s[ind] - 'A' + 27;
                    }
                    cnt[pos][x]++
                }
            }

            for (ind in 0..54) {
                if (cnt[0][ind] != 0 && cnt[1][ind] != 0 && cnt[2][ind] != 0) {
                    res += ind
                    break
                }
            }
            i += 3
        }
        return res
    }


    fun checkTests(input: List<String>) {
        check(part1(input) == 157)
        check(part2(input) == 70)
    }


    val input = readInput("Day03")
    val input_test = readInput("Day03_test")

    checkTests(input_test)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}


