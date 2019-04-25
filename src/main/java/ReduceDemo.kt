import majorleague.Player
import java.nio.file.Files
import java.nio.file.Paths

class ReduceDemo {
    fun testReduce() {
        val path = Paths.get("C:\\Users\\kitri-03\\IdeaProjects\\HelloKotlin\\src\\main\\resources\\Salaries.csv")

        // 2000년 이후 내셔널 리그의 연봉 TOP 5 출력
        val reduce = Files.lines(path)       // Stream<String>
            .skip(1)         // header line skip
            .map { line ->
                val items = line.split(",")
                Player(items[0].toInt(), items[1], items[2], items[3], items[4].toLong())
            }   // Stream<Player>
            .mapToLong(Player::salary)
            .summaryStatistics()
//            .limit(20)
//            .map(Player::salary)    // Stream<Long>
//            .reduce(0L, Long::plus)

        println(reduce)
    }
}

fun main() {
    val app = ReduceDemo()

    app.testReduce()
}