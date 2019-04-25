package Food

data class Dish (val name: String, val vegetarian: Boolean, val calories: Int, val type: Type) {
    enum class Type { MEAT, FISH, OTHER }
}