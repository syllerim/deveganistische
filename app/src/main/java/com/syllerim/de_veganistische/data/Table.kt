package com.syllerim.de_veganistische.data

import java.io.Serializable

data class Table(var id:Int, var name: String, var dishes: MutableList<Int>, var totalOrder: Double): Serializable {
    override fun toString() = name
}