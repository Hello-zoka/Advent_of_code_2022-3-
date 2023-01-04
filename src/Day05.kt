import java.io.File

fun main() {
    val cur = listOf(
        "WRF".toMutableList(),
        "THMCDVWP".toMutableList(),
        "PMZNL".toMutableList(),
        "JCHR".toMutableList(),
        "CPGHQTB".toMutableList(),
        "GCWLFZ".toMutableList(),
        "WVLQZJGC".toMutableList(),
        "PNRFWTVC".toMutableList(),
        "JWHGRSV".toMutableList()
    ).toMutableList()

    val cur_test = listOf(
        "ZN".toMutableList(),
        "MCD".toMutableList(),
        "P".toMutableList(),
    ).toMutableList()


    fun part1(input: List<String>): String {
        for (i in input) {
            val parse = i.split(" ").toTypedArray()
            val cnt = parse[1].toInt()
            val from = parse[3].toInt() - 1
            val to = parse[5].toInt() - 1
            for (c in 1..cnt) {
                cur[to].add(cur[from].last())
                cur[from] = cur[from].dropLast(1).toMutableList()
            }
        }
        var res = ""
        for (i in cur){
            res += i.last()
        }
        return res
    }

    fun part2(input: List<String>): String {
        for (i in input) {
            val parse = i.split(" ").toTypedArray()
            val cnt = parse[1].toInt()
            val from = parse[3].toInt() - 1
            val to = parse[5].toInt() - 1
            for (pos in cur[from].size - cnt..cur[from].size - 1) {
                cur[to].add(cur[from][pos])
            }
            cur[from] = cur[from].dropLast(cnt).toMutableList()
//            println(cnt)
//            println(from)
//            println(to)
//            for (row in cur){
//                println(row)
//            }
        }
        var res = ""
        for (i in cur){
            res += i.last()
        }
        return res
    }


    fun checkTests(input: List<String>) {
        check(part1(input) == "CMZ")
        check(part2(input) == "MCD")
    }



    val input = readInput("Day05")
    val input_test = readInput("Day05_test")

//     checkTests(input_test)
    // need to swap cur and cur_test for testing
//    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
    // parts should be independent because of common list cur
}


