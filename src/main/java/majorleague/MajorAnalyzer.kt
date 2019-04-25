package majorleague
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Comparator

class MajorAnalyzer {
    fun testFileStream() {
        val path = Paths.get("C:\\Users\\kitri-03\\IdeaProjects\\HelloKotlin\\src\\main\\resources\\Salaries.csv")

        // 2000년 이후 내셔널 리그의 연봉 TOP 5 출력

        println(Files.lines (path)       // Stream<String>
            .skip (1)         // header line skip
            .map { line ->
                val items = line.split(",")
                Player(items[0].toInt(), items[1], items[2], items[3], items[4].toLong())
            }   // Stream<Player>
            .anyMatch { player -> player.league == "NL" && player.salary >= 30_000_000 })
//            .filter { player -> player.year >= 2000 }
//            .filter { player -> player.league == "NL" }
//            .sorted (Comparator.comparingLong(Player::salary).reversed())
//            .limit (5)
//            .filter { player -> player.salary >= 30_000_000}
//            .peek { println(it) }
//            .mapToLong(Player::salary)
//            .boxed()
//            .sorted { o1, o2 -> o1.compareTo(o2) }
//            .forEach { println(it) }
    }
}

fun main() {
    val app = MajorAnalyzer()
    app.testFileStream()
}