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