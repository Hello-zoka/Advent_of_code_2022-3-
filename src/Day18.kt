import java.util.*

import java.lang.Integer.max


fun main() {
    fun part1(input: List<String>): Int {
        val n = input.size
        val ans = MutableList(n) { 6 }
        val used = MutableList(n) { 0 }
        val ind = emptyMap<Triple<Int, Int, Int>, Int>().toMutableMap()
        val lst = mutableListOf<Triple<Int, Int, Int>>()

        var pos = 0
        for (i in input) {
            val parse = i.split(",").toTypedArray()
            lst.add(Triple(parse[0].toInt(), parse[1].toInt(), parse[2].toInt()))
            ind[lst.last()] = pos++
        }
        val q = listOf<Int>().toMutableList()
        val p1 = listOf<Int>(1, -1, 0, 0, 0, 0)
        val p2 = listOf<Int>(0, 0, 1, -1, 0, 0)
        val p3 = listOf<Int>(0, 0, 0, 0, 1, -1)

        for (i in lst) {
            var v = ind[i]!!
            if (used[v] == 0) {
                q.add(v)
                while (!q.isEmpty()) {
                    v = q.last()
                    used[v] = 1
                    q.removeLast()
                    val vv = lst[v]
                    for (k in 0..p1.size - 1) {
                        val nxt = Triple(vv.first + p1[k], vv.second + p2[k], vv.third + p3[k])
                        if (!ind.containsKey(nxt)) continue
                        val to = ind[nxt]!!
                        if (used[to] == 1) continue
                        ans[v]--
                        if (ans[to]-- == 6) {
                            q.add(to)
                        }
                    }
                }
            }
        }
        return ans.sum()
    }

    fun part2(input: List<String>): Int {
        val q = listOf<Int>().toMutableList()
        val p1 = listOf<Int>(1, -1, 0, 0, 0, 0)
        val p2 = listOf<Int>(0, 0, 1, -1, 0, 0)
        val p3 = listOf<Int>(0, 0, 0, 0, 1, -1)
        val n = input.size

        val ind = emptyMap<Triple<Int, Int, Int>, Int>().toMutableMap()
        val ind2 = emptyMap<Triple<Int, Int, Int>, Int>().toMutableMap()
        val lst = mutableListOf<Triple<Int, Int, Int>>()
        val lst2 = mutableListOf<Triple<Int, Int, Int>>()
        val bad = mutableMapOf<Triple<Int, Int, Int>, Int>()

        var pos = 0
        for (i in input) {
            val parse = i.split(",").toTypedArray()
            lst.add(Triple(parse[0].toInt(), parse[1].toInt(), parse[2].toInt()))
            ind[lst.last()] = pos++
        }
        var pos2 = 0

        for (x in 0..101) {
            for (y in 0..101) {
                for (z in 0..101) {
                    if (!ind.containsKey(Triple(x, y, z))) {
                        ind2[Triple(x, y, z)] = pos2++
                        lst2.add(Triple(x, y, z))
                    }
                }
            }
        }
        q.add(0)
        while (!q.isEmpty()) {
            val v = q.last()
            bad[lst2[v]] = 1
            q.removeLast()
            val vv = lst2[v]
            for (k in 0..p1.size - 1) {
                val nxt = Triple(vv.first + p1[k], vv.second + p2[k], vv.third + p3[k])
                if (ind.containsKey(nxt)) continue
                if (!ind2.containsKey(nxt)) continue
                val to = ind2[nxt]!!
                if (bad.containsKey(lst2[to])) continue
                q.add(to)
            }
        }
        println("BAD FOUND")
        for (x in 0..101) {
            for (y in 0..101) {
                for (z in 0..101) {
                    if (!ind.containsKey(Triple(x, y, z)) && !bad.containsKey(Triple(x, y, z))) {
                        ind[Triple(x, y, z)] = pos++
                        lst.add(Triple(x, y, z))
                    }
                }
            }
        }
        var ans = MutableList(pos) { 6 }
        var used = MutableList(pos) { 0 }
        q.clear()
        for (i in lst) {
            var v = ind[i]!!
            if (used[v] == 0) {
                q.add(v)
                while (!q.isEmpty()) {
                    v = q.last()
                    used[v] = 1
                    q.removeLast()
                    val vv = lst[v]
                    for (k in 0..p1.size - 1) {
                        val nxt = Triple(vv.first + p1[k], vv.second + p2[k], vv.third + p3[k])
                        if (!ind.containsKey(nxt)) continue
                        val to = ind[nxt]!!
                        if (used[to] == 1) continue
                        ans[v]--
                        if (ans[to]-- == 6) {
                            q.add(to)
                        }
                    }
                }
            }
        }
        return ans.sum()
    }


    fun checkTests(input: List<String>) {
        check(part1(input) == 64)
        check(part2(input) == 58)
    }


    val input = readInput("Day18")
    val input_test = readInput("Day18_test")

    checkTests(input_test)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

