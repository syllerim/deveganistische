package com.syllerim.de_veganistische.data

object Tables {

    private val tables: List<Table> = listOf(
            Table(1,"Table 1", arrayOf(0, 2, 3, 4), 40.30),
            Table(2,"Table 2", arrayOf(1, 4), 7.0 ),
            Table(3,"Table 3", arrayOf(1, 3, 4), 26.2),
            Table(4,"Table 4", arrayOf(1, 4), 10.4),
            Table(5,"Table 5", arrayOf(1, 3, 4), 24.2),
            Table(6,"Table 6", arrayOf(1, 3), 23.1),
            Table(7,"Table 7", arrayOf(1, 2, 4), 10.0),
            Table(8,"Table 8", arrayOf(), 0.0),
            Table(9,"Table 9", arrayOf(3), 1.0)
    )

    var allItems = tables
        private set

    val count
        get() = tables.size

    fun getTable(index: Int) = tables[index]

    fun getIndex(table: Table) = tables.indexOf(table)

    operator fun get(index: Int) = tables[index]

    fun toArray() = tables.toTypedArray()

    fun allDishesForTable(index: Int) = tables[index].dishes
}