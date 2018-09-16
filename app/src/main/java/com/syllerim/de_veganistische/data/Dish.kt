package com.syllerim.de_veganistische.data

import java.io.Serializable

data class Dish(var id: Int,
                var name: String,
                var description: String,
                var icon: Int,
                var price: Double,
                var ingredients: Array<String>,
                var allergens: Array<String>,
                var typeMenu: Int): Serializable {
    override fun toString() = name
}