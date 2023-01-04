import java.lang.Math.abs
import java.lang.Integer.max
import kotlin.math.sign


fun main() {
    fun part1(input: List<String>): Int {
        val used = MutableList(1000){ MutableList(1000){0}}
        var x = 500
        var y = 500
        var curx = 500
        var cury = 500
        used[curx][cury] = 1
        var res = 1
        val mv = mutableMapOf("U" to Pair(0, 1), "D" to Pair(0, -1), "R" to Pair(1, 0), "L" to Pair(-1, 0))
        for (i in input) {
            val parse = i.split(" ").toTypedArray()
            for (j in 1..parse[1].toInt()) {
                val xx = x + mv[parse[0]]!!.first
                val yy = y + mv[parse[0]]!!.second
                if (abs(xx - curx) > 1 || abs(yy - cury) > 1) {
                    curx = x
                    cury = y
                    if (used[x][y]++ == 0) {
                        res++
                    }
                }
                x = xx
                y = yy
            }
        }
        return res
    }

    fun part2(input: List<String>): Int {
        val used = MutableList(1000) { MutableList(1000) { 0 } }
        val x = 500
        val y = 500
        var res = 0
        val lst = mutableListOf(
            Pair(x, y),
            Pair(x, y),
            Pair(x, y),
            Pair(x, y),
            Pair(x, y),
            Pair(x, y),
            Pair(x, y),
            Pair(x, y),
            Pair(x, y),
            Pair(x, y)
        )
        val mv = mutableMapOf(
            "U" to Pair(0, 1),
            "D" to Pair(0, -1),
            "R" to Pair(1, 0),
            "L" to Pair(-1, 0)
        )
        for (i in input) {
            val parse = i.split(" ").toTypedArray()
            for (j in 1..parse[1].toInt()) {
                lst[0] += mv[parse[0]]!!
                for (pos in 1..lst.size - 1) {
                    while (max(abs(lst[pos].first - lst[pos -1].first), abs(lst[pos].second - lst[pos - 1].second)) > 1) {
                        lst[pos] += Pair((lst[pos - 1].first - lst[pos].first).sign, (lst[pos - 1].second - lst[pos].second).sign)
                        if (used[lst.last().first][lst.last().second]++ == 0) {
                            res++
                        }
                    }
                }
            }
        }
        return res
    }


    fun checkTests(input: List<String>) { // different tests
//        check(part1(input) == 13)
        check(part2(input) == 36)
    }


    val input = readInput("Day09")
    val input_test = readInput("Day09_test")

    checkTests(input_test)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

