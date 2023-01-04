import java.io.File

fun main() {
    fun contain(a: Pair<Int, Int>, b: Pair<Int, Int>) = a.first <= b.first && b.second <= a.second

    // a contains b
    fun overlap(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        fun pointInSeg(seg: Pair<Int, Int>, pt: Int) = seg.first <= pt && pt <= seg.second
        return pointInSeg(b, a.first) || pointInSeg(b, a.second) || pointInSeg(a, b.first) || pointInSeg(a, b.second)
    }

    fun part1(input: List<String>): Int {
        var res = 0
        for (i in input) {
            val ranges = i.split(",").toTypedArray()
            val a = ranges[0].split('-').toTypedArray().map { it.toInt() }
            val b = ranges[1].split('-').toTypedArray().map { it.toInt() }
            val r1 = Pair(a[0], a[1])
            val r2 = Pair(b[0], b[1])
            if (contain(r1, r2) || contain(r2, r1)) res++
        }
        return res
    }

    fun part2(input: List<String>): Int {
        var res = 0
        for (i in input) {
            val ranges = i.split(",").toTypedArray()
            val a = ranges[0].split('-').toTypedArray().map { it.toInt() }
            val b = ranges[1].split('-').toTypedArray().map { it.toInt() }
            if (overlap(Pair(a[0], a[1]), Pair(b[0], b[1]))) res++
        }
        return res
    }


    fun checkTests(input: List<String>) {
        check(part1(input) == 2)
        check(part2(input) == 4)
    }


    val input = readInput("Day04")
    val input_test = readInput("Day04_test")

    checkTests(input_test)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

