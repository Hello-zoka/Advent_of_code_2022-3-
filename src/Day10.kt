fun main() {
    fun calcVal(input: List<String>) : List<Int> {
        val res = mutableListOf(1)
        for (i in input) {
            res.add(res.last())
            val parsed = i.split(" ").toTypedArray()
            if (parsed[0] == "addx") {
                res.add(res.last() + parsed[1].toInt())
            }
        }
        return res
    }
    fun part1(input: List<String>): Int {
        val values = calcVal(input)
        var res = 0
        for (i in 0..values.size - 1) {
            if ((i - 19) % 40 == 0 && i <= 219) {
                res += values[i] * (i + 1)
            }
        }
        return res
    }

    fun part2(input: List<String>): String {
        val values = calcVal(input).dropLast(1)
        val conv = values.mapIndexed{ind, x -> if (ind % 40 >= x - 1 && ind % 40 <= x + 1) '#' else '.'}.chunked(40)
        var res = ""
        for (i in 0..conv.size - 1) {
            res += '\n'
            for (j in conv[i]) {
                res += j
            }
        }
        return res
    }


    fun checkTests(input: List<String>) {
        println(part1(input))
        check(part1(input) == 13140)
        check(part2(input) == "\n##..##..##..##..##..##..##..##..##..##..\n" +
                "###...###...###...###...###...###...###.\n" +
                "####....####....####....####....####....\n" +
                "#####.....#####.....#####.....#####.....\n" +
                "######......######......######......####\n" +
                "#######.......#######.......#######.....")
    }


    val input = readInput("Day10")
    val input_test = readInput("Day10_test")

    checkTests(input_test)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
    // result of part2
//    ###..###....##.#....####.#..#.#....###..
//    #..#.#..#....#.#....#....#..#.#....#..#.
//    ###..#..#....#.#....###..#..#.#....#..#.
//    #..#.###.....#.#....#....#..#.#....###..
//    #..#.#.#..#..#.#....#....#..#.#....#....
//    ###..#..#..##..####.#.....##..####.#....
    // BRJLFULP
}

