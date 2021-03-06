package com.syllerim.de_veganistische.data

import com.syllerim.de_veganistische.R

object Dishes {

    private val dishes: List<Dish> = listOf(
        Dish(1,
                "Spinach Dip",
                "Vegan and delicious healthy carbs salad",
                R.drawable.ic_spinach_dip,
                5.0,
                arrayOf("Onions", "Spinach", "Paprika", "Garlic", "Salt", "25 g Nutritional yeast"),
                arrayOf("gluten free"),
                1 ),
        Dish(2,
                "Quinoa Salad",
                "Vegan and delicious healthy carbs salad",
                R.drawable.ic_quinoa_salad,
                10.87,
                arrayOf("Quinoa", "lettuce", "cacahuetes"),
                arrayOf("cacahuetes"),
                2),
        Dish(3,
                "Balsamic Tomato and Pesto Canapés",
                "Balsamic Tomato and Pesto Canapés Delicious",
                R.drawable.ic_balsamic_tomato,
                10.87,
                arrayOf("Onions", "Spinach", "Paprika", "Garlic", "Salt", "25 g Nutritional yeast"),
                arrayOf("gluten free"),
                6),
        Dish(4,
                "Coconut Chickpea Curry.",
                "Balsamic Tomato and Pesto Canapés Delicious",
                R.drawable.ic_chickpea_curry,
                14.9,
                arrayOf("Potatoes", "Vegan Meatloaf"),
                arrayOf("gluten free"),
                3),
        Dish(5,
                "Lentil soup",
                "Delicious green letil soup",
                R.drawable.ic_lentil_soup,
                10.9,
                arrayOf("Lentils", "Potatoe", "Koriander", "Egg Plant", "Onions", "Apio"),
                arrayOf("Apio"),
                5),
        Dish(6,
                "Oats and Apple cake",
                "Delicious individual cake",
                R.drawable.ic_oatmeal_cake,
                10.9,
                arrayOf("Lentils", "Potatoe", "Koriander", "Egg Plant", "Onions", "Apio"),
                arrayOf("Apio"),
                4)

    )

    var allItems = dishes
        private set

    val count
        get() = dishes.size

    fun getDish(index: Int) = dishes[index]

    fun getIndex(dish: Dish) = dishes.indexOf(dish)

    operator fun get(index: Int) = dishes[index]

    fun toArray() = dishes.toTypedArray()

    fun allDishesOf(type: Int) = allItems.filter { it.typeMenu == type }

    fun dish(id: Int) = allItems.filter { it.id == id }.first()

}