import Food.*

import java.util.*
import java.util.function.Function
import java.util.function.Predicate
import java.util.stream.Collectors
import java.util.stream.IntStream
import java.util.stream.Stream
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

        val predicate = Predicate<Apple>{apple -> "green" == apple.color}
        val result = list.stream().filter(predicate
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
        val newData = mapString(lists, f.andThen(g).compose(h))
        println(newData)
    }

    private fun mapString(lists: List<String>, f: Function<String, String>): List<String> {
        val newList = ArrayList<String>()
        for (item in lists) {
            newList.add(f.apply(item))
        }
        return newList
    }

    fun testStream() {
        val intArr = intArrayOf(1, 2, 3, 4, 5)
        Arrays.stream(intArr)

        val dishList = getDishLists()
        println(dishList)
        val streamList = dishList!!.stream()
                                        .filter{ dish -> dish.calories >= 400 }// 400 칼로리 이상
                                        .sorted(Comparator.comparingInt(Dish::calories))// 칼로리로 정렬
                                        .map(Dish::name)// 요리를 이름으로 바꿈 -- 여기서 type이 바뀜 Stream<Dish> -> Stream<String>으로...
                                        .limit(3)// 상위 3개만 출력
                                        .toList()
//                                        .collect(Collectors.toList())// List<String>으로 type변경
        println(streamList)
    }

    fun getDishLists(): MutableList<Dish>? {
        return Arrays.asList(
            Dish("pork", false, 800, Dish.Type.MEAT),
            Dish("beef", false, 700, Dish.Type.MEAT),
            Dish("chicken", false, 400, Dish.Type.MEAT),
            Dish("french fries", true, 530, Dish.Type.OTHER),
            Dish("rice", true, 350, Dish.Type.OTHER),
            Dish("season fruit", true, 120, Dish.Type.OTHER),
            Dish("pizza", true, 550, Dish.Type.OTHER),
            Dish("prawns", false, 400, Dish.Type.FISH),
            Dish("salmon", false, 450, Dish.Type.FISH))
    }
}

fun main() {
    println("Hello Kotlin!")
    val app = Hellokotlin()

    app.testPredicate()

    app.functionComposition()

    app.testStream()

//    Stream.iterate(0){ n -> n + 2 }.limit(10).forEach{ println(it) }
    Stream.generate(Math::random)
        .mapToInt { x -> (x * 45+1).toInt() }
        .distinct()
        .limit(6)
        .forEach { println(it) }

    val average = Stream.generate(Math::random)
        .mapToInt { x -> (x * 45 + 1).toInt() }
        .distinct()
        .limit(6)
        .average()

    println(average?.asDouble?:0)
}

