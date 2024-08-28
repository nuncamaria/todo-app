package com.nuncamaria.getthingsdone.ui.utils

import com.nuncamaria.getthingsdone.data.ToDoModel
import com.nuncamaria.getthingsdone.domain.ToDosLinkedHashMap

sealed interface UiState<out T> {
    data object Idle : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error<out T>(val e: Throwable, val message: String = "") : UiState<T>
}

/** Ui State for ToDoListView */
data class ToDoListUiState(val toDoList: ToDosLinkedHashMap = ToDosLinkedHashMap())

/** Ui State for AddToDoView */
data class ToDoModelState(
    val toDoModel: ToDoModel = ToDoModel(),
    val isEntryValid: Boolean = false
)