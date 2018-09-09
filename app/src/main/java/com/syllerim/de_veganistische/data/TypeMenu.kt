package com.syllerim.de_veganistische.data

import java.io.Serializable

data class TypeMenu(var id: Int, var name: String, var icon: Int): Serializable {
     override fun toString() = name
}