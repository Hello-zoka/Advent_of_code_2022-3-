import java.util.Objects

abstract class Object() {
    abstract val sz: Int
    abstract val child: MutableMap<String, Object>

}

class File(
    override val sz: Int,
    override val child: MutableMap<String, Object> = mutableMapOf()
) : Object()

class Dir(
    par: Dir?,
    override val child: MutableMap<String, Object> = mutableMapOf()
) : Object() {
    val parent = par
    override val sz: Int
        get() = child.values.sumOf { it.sz }


    fun addFile(fname: String, fsz: Int) {
        if (!child.containsKey(fname)) {
            child[fname] = File(fsz)
        }
    }

    fun addDir(dname: String) {
        if (!child.containsKey(dname)) {
            child[dname] = Dir(this)
        }
    }
}


fun main() {
    val sizes = mutableListOf<Int>()
    fun calc(cur: Object) {
        if (cur.child.isNotEmpty())
            sizes.add(cur.sz)
        cur.child.values.forEach { calc(it) }
    }

    fun build_tree(input: List<String>) {
        sizes.clear()
        val root = Dir(null)
        var cur = root
        for (i in input) {
            val parsed = i.split(" ").toTypedArray()
            if (parsed[0] == "$") {
                when (parsed[1]) {
                    "cd" -> when (parsed[2]) {
                        "/" -> cur = root
                        ".." -> cur = cur.parent!!
                        else -> {
                            if (!cur.child.containsKey(parsed[2]))
                                cur.addDir(parsed[2])
                            cur = cur.child.get(parsed[2]) as Dir
                        }
                    }

                    else -> {}
                }
            } else if (parsed[0] == "dir") {
                cur.addDir(parsed[1])
            } else {
                cur.addFile(parsed[1], parsed[0].toInt())
            }
        }
        calc(root)
    }

    fun part1(input: List<String>): Int {
        build_tree(input)
        return sizes.filter { it <= 100000 }.sum()
    }

    fun part2(input: List<String>): Int {
        build_tree(input)
        val sz = sizes[0]
        return sizes.filter { 30000000 <= 70000000 + it - sz }.min()
    }


    fun checkTests(input: List<String>) { // There are more than one test, use your hands)
        check(part1(input) == 95437)
        check(part2(input) == 24933642)
    }


    val input = readInput("Day07")
    val input_test = readInput("Day07_test")

    checkTests(input_test)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

