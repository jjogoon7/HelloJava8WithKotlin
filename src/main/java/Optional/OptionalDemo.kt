package Optional

import majorleague.Player
import java.util.*
import java.util.function.Consumer

class OptionalDemo {
    fun testOptional() {
        val player = getPlayerObject(-1)
//        val p = player.orElse(Player(2019, "KITRI", "KR", "backup", 100_000))
//        player.ifPresent { println(it) }
//        println(p)
//        player.ifPresent { p -> println(p) }
        player.ifPresentOrElse(Consumer { p -> println(p) }, Runnable { println("No data!!!") })
    }

    private fun getPlayerObject(salary: Long): Optional<Player> {
        if (salary <= 0L) {
            return Optional.empty()
        }
        return Optional.of(Player(2019, "KITRI", "KR", "kwcho", salary))
    }
}

fun main() {
    val app = OptionalDemo()
    app.testOptional()
}