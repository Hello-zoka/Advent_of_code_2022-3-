fun main() {
    fun from(s: String): Long {
        val parse = mapOf('0' to 0L, '1' to 1L, '2' to 2L, '-' to -1L, '=' to -2L)
        var res = 0L
        var st = 1L
        for (i in s.length - 1 downTo 0) {
            res += st * (parse[s[i]]!!)
            st *= 5
        }
        return res
    }

    fun to(N: Long): String {
        var n = N
        val parse = mapOf(0L to '0', 1L to '1', 2L to '2', -1L to '-', -2L to '=')
        var res = ""
        while (n > 0L) {
            var x = n % 5
            if (x >= 3L) {
                n += 5
                x -= 5
            }
            res += parse[x]
            n /= 5
        }
        return res.reversed()
    }

    fun part1(input: List<String>): String {
        var res = 0L
        for (i in input) {
            res += from(i)
        }
        return to(res)
    }

    fun checkTests(input: List<String>) {
        check(part1(input) == "2=-1=0")
    }


    val input = readInput("Day25")
    val input_test = readInput("Day25_test")

    checkTests(input_test)
    println("Part 1: ${part1(input)}")
    println("(i)UUUURRRRRAAAAA")
}

