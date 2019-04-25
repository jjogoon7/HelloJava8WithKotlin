package collect

import majorleague.Player
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors.*

class Grouping02 {
    fun testGrouping02() {
        val file = Paths.get("C:\\Users\\kitri-03\\IdeaProjects\\HelloKotlin\\src\\main\\resources\\Salaries.csv")
        val collect = Files.lines(file) // Stream<String>
            .skip(1)    // header line skip
            .map { line ->
                val items = line.split(",")
                Player(items[0].toInt(), items[1], items[2], items[3], items[4].toLong())
            }   // Stream<Player>
//            .filter { p -> p.salary > 1_000_000 }  // Stream<Player>
            .collect(
                partitioningBy(
                    { p -> p.salary >= 10_000_000 },
                    groupingBy(Player::league, counting())// Map<String, Long>
            ))

        println(collect)
    }
}

fun main() {
    val app = Grouping02()
    app.testGrouping02()
}