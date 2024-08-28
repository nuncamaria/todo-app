package com.nuncamaria.getthingsdone.ui.todoentry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nuncamaria.getthingsdone.data.ToDoListRepository
import com.nuncamaria.getthingsdone.data.ToDoModel
import com.nuncamaria.getthingsdone.ui.utils.ToDoModelState

class AddToDoViewModel(private val repository: ToDoListRepository) : ViewModel() {

    var toDoItemState by mutableStateOf(ToDoModelState())
        private set

    fun updateInputState(item: ToDoModel) {
        toDoItemState = ToDoModelState(
            toDoModel = item,
            isEntryValid = validateInput(item)
        )
    }

    suspend fun saveToDo() {
        if (validateInput()) {
            repository.insertToDoItem(toDoItemState.toDoModel)
        }
    }

    private fun validateInput(uiState: ToDoModel = toDoItemState.toDoModel): Boolean {
        return with(uiState) {
            title.isNotBlank() && description.isNotBlank()
        }
    }
}