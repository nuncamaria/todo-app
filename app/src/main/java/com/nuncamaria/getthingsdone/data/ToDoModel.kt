package com.nuncamaria.getthingsdone.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ToDoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val isDone: Boolean = false
)

enum class ToDoStatus {
    TO_DO,
    DONE,
}

fun ToDoStatus.getStatusTitle(): String = when (name) {
    ToDoStatus.TO_DO.name -> "To do"
    ToDoStatus.DONE.name -> "Done"
    else -> "Empty"
}