package collect

import majorleague.Player
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors.groupingBy
import java.util.stream.Collectors.summarizingDouble

class Grouping01 {
    fun testGrouping01() {
        val file = Paths.get("C:\\Users\\kitri-03\\IdeaProjects\\HelloKotlin\\src\\main\\resources\\Salaries.csv")
        val collect = Files.lines(file) // Stream<String>
            .skip(1)    // header line skip
            .map { line ->
                val items = line.split(",")
                Player(items[0].toInt(), items[1], items[2], items[3], items[4].toLong())
            }   // Stream<Player>
            .filter { p -> p.salary > 10_000_000 }  // Stream<Player>
            .collect(groupingBy(
                Player::year,                   // Map<String, List<Player>>
                summarizingDouble { p -> p.salary.toDouble() })) // Map<String, Long>

        println(collect[2016]?.max?:0)
    }
}

fun main() {
    val app = Grouping01()
    app.testGrouping01()
}