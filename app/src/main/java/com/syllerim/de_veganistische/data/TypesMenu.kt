package com.syllerim.de_veganistische.data

import com.syllerim.de_veganistische.R

object TypesMenu {

    private val menu: List<TypeMenu> = listOf(
            TypeMenu(1, "Starters", R.drawable.ic_starters),
            TypeMenu(2, "Salads", R.drawable.ic_salads),
            TypeMenu(3, "Main Courses", R.drawable.ic_main_courses),
            TypeMenu(4, "Desserts", R.drawable.ic_desserts),
            TypeMenu(5, "Soup", R.drawable.ic_soups),
            TypeMenu(6, "Hors d'oeuvres.", R.drawable.ic_hors_doeuvres)
    )

    var allItems = menu
        private set

    val count
        get() = menu.size

    fun getMenu(index: Int) = menu[index]

    fun getIndex(typeOfMenu: TypeMenu) = menu.indexOf(typeOfMenu)

    operator fun get(index: Int) = menu[index]

    fun toArray() = menu.toTypedArray()

    fun allTypeMenu(index: Int) = menu[index]

    fun typeMenu(id: Int) = allItems.filter { it.id == id }.first()
}