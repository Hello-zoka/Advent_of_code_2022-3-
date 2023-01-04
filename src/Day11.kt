data class Monkey(val vals: MutableList<Long>, val mod: (Long) -> Long, val next: (Long) -> Int)

fun makeTest(mod: Long, cor: Int, inc: Int) = { x: Long -> if (x % mod == 0L) cor else inc }

fun main() {

    fun part1(): Int {
        val lst = listOf(
            Monkey(mutableListOf(83, 62, 93), { x -> x * 17 }, makeTest(2, 1, 6)),
            Monkey(mutableListOf(90, 55), { x -> x + 1 }, makeTest(17, 6, 3)),
            Monkey(mutableListOf(91, 78, 80, 97, 79, 88), { x -> x + 3 }, makeTest(19, 7, 5)),
            Monkey(mutableListOf(64, 80, 83, 89, 59), { x -> x + 5 }, makeTest(3, 7, 2)),
            Monkey(mutableListOf(98, 92, 99, 51), { x -> x * x }, makeTest(5, 0, 1)),
            Monkey(mutableListOf(68, 57, 95, 85, 98, 75, 98, 75), { x -> x + 2 }, makeTest(13, 4, 0)),
            Monkey(mutableListOf(74), { x -> x + 4 }, makeTest(7, 3, 2)),
            Monkey(mutableListOf(68, 64, 60, 68, 87, 80, 82), { x -> x * 19 }, makeTest(11, 4, 5)),
        )

        val cnt = MutableList(lst.size) { 0 }
        for (i in 1..20) {
            for (j in 0..lst.size - 1) {
                lst[j].vals.forEach{
                    val x = lst[j].mod(it) / 3
                    lst[lst[j].next(x)].vals.add(x)
                    cnt[j]++
                }
                lst[j].vals.clear()
            }
        }
        val sorted = cnt.sorted()
        return sorted[cnt.size - 1] * sorted[cnt.size - 2]
    }

    fun part2(): Long {
        val MOD = 2L * 17L * 19L * 3L * 5L * 13L * 7L * 11L

        val lst = listOf(
            Monkey(mutableListOf(83, 62, 93), { x -> x * 17 }, makeTest(2, 1, 6)),
            Monkey(mutableListOf(90, 55), { x -> x + 1 }, makeTest(17, 6, 3)),
            Monkey(mutableListOf(91, 78, 80, 97, 79, 88), { x -> x + 3 }, makeTest(19, 7, 5)),
            Monkey(mutableListOf(64, 80, 83, 89, 59), { x -> x + 5 }, makeTest(3, 7, 2)),
            Monkey(mutableListOf(98, 92, 99, 51), { x -> x * x }, makeTest(5, 0, 1)),
            Monkey(mutableListOf(68, 57, 95, 85, 98, 75, 98, 75), { x -> x + 2 }, makeTest(13, 4, 0)),
            Monkey(mutableListOf(74), { x -> x + 4 }, makeTest(7, 3, 2)),
            Monkey(mutableListOf(68, 64, 60, 68, 87, 80, 82), { x -> x * 19 }, makeTest(11, 4, 5)),
        )
        val cnt = MutableList(lst.size) { 0L }
        for (i in 1..10000) {
            for (j in 0..lst.size - 1) {
                lst[j].vals.forEach{
                    val x = lst[j].mod(it)
                    lst[lst[j].next(x)].vals.add(x % MOD)
                    cnt[j]++
                }
                lst[j].vals.clear()
            }
        }
        val sorted = cnt.sorted()
        return sorted[cnt.size - 1] * sorted[cnt.size - 2]
    }


//    val input = readInput("Day10")
//    val input_test = readInput("Day10_test")

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")

}

