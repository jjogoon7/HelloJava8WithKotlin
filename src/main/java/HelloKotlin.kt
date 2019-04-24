import Fruit.*
import java.util.*
import java.util.function.Function
import java.util.function.Predicate
import kotlin.collections.ArrayList
import kotlin.streams.toList

class Hellokotlin {
    fun filterApples(inventory: List<Apple>, p: Predicate<Apple>) : List<Apple> {
        val result = ArrayList<Apple>()
        for(apple in inventory) {
            if (p.test(apple)) {
                result.add(apple)
            }
        }
        return result
    }

    fun testPredicate() {
        val list = Arrays.asList(
            Apple("red", 150),
            Apple("green", 100),
            Apple("red", 110),
            Apple("green", 125),
            Apple("red", 163),
            Apple("green", 177),
            Apple("green", 149)
        )

        val redicate = Predicate<Apple>{apple -> "green" == apple.color}
        val result = list.stream().filter(redicate
            .and{apple -> apple.weight >= 150}
            .negate()
            .or{apple -> apple.weight > 200}).toList()

//    val result = list.stream().map{apple -> apple.color}.toList()

//    val result = app.filterApples(list, Predicate { apple -> "green" == apple.color && apple.weight >= 150 })
        println(result)
    }

    fun functionComposition() {
        val f = Function<String, String> { a -> a.toUpperCase() }
        val g = Function<String, String> { a -> "$a...zzz" }
        val h = Function<String, String> { a -> a.toLowerCase() }

        val lists = Arrays.asList("kim", "lee", "park")
        val newData = mapString(lists, f.andThen(g))
        println(newData)
    }

    fun mapString(lists: List<String>, f: Function<String, String>): List<String> {
        val newList = ArrayList<String>()
        for (item in lists) {
            newList.add(f.apply(item))
        }
        return newList
    }
}

fun main() {
    println("Hello Kotlin!")
    val app = Hellokotlin()

    app.testPredicate()

    app.functionComposition()
}

