package com.chogo.lostandfound

enum class ItemType(val label: String) {
    LOST("Lost"),
    FOUND("Found");

    companion object {
        fun fromName(name: String?): ItemType =
            values().firstOrNull { it.name == name } ?: LOST
    }
}

object Categories {
    const val ALL = "All"
    val list = listOf("Electronics", "Documents", "Bags", "Clothing", "Keys", "Other")
}

data class Item(
    val id: Long,
    val name: String,
    val description: String,
    val location: String,
    val date: String,
    val contact: String,
    val category: String,
    val type: ItemType,
    var claimed: Boolean = false
)