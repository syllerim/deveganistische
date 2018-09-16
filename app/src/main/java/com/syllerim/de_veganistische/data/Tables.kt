package com.syllerim.de_veganistische.data

object Tables {

    private var tables: MutableList<Table> = mutableListOf(
            Table(1,"Table 1", mutableListOf(0, 2, 3, 4), 40.30),
            Table(2,"Table 2", mutableListOf(1, 4), 7.0 ),
            Table(3,"Table 3", mutableListOf(1, 3, 4), 26.2),
            Table(4,"Table 4", mutableListOf(1, 4), 10.4),
            Table(5,"Table 5", mutableListOf(1, 3, 4), 24.2),
            Table(6,"Table 6", mutableListOf(1, 3), 23.1),
            Table(7,"Table 7", mutableListOf(1, 2, 4), 10.0),
            Table(8,"Table 8", mutableListOf(), 0.0),
            Table(9,"Table 9", mutableListOf(3), 1.0)
    )

    var allItems = tables
        private set

    val count
        get() = tables.size

    fun getTable(index: Int) = tables[index]

    fun getIndex(table: Table) = tables.indexOf(table)

    operator fun get(index: Int) = tables[index]

    fun toArray() = tables.toTypedArray()

    fun add(dishId: Int, tableId: Int) {
        tables.filter { it.id == tableId }.first().dishes.add(dishId)
    }

    fun remove(dishAtPosition: Int, tableId: Int) {
        tables.filter { it.id == tableId }.first().dishes.removeAt(dishAtPosition-1)
    }

    fun table(id: Int) = Tables.allItems.filter { it.id == id }.first()

    fun allDishesForTable(id: Int) = Tables.allItems.filter { it.id == id }.first().dishes
}