package com.nuncamaria.getthingsdone.ui.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuncamaria.getthingsdone.data.ToDoListRepository
import com.nuncamaria.getthingsdone.data.ToDoModel
import com.nuncamaria.getthingsdone.domain.GetToDoListUseCase
import com.nuncamaria.getthingsdone.ui.utils.ToDoListUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ToDoListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase,
    private val repository: ToDoListRepository,
) : ViewModel() {

    val toDoList: StateFlow<ToDoListUiState> =
        getToDoListUseCase().map { ToDoListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = ToDoListUiState()
            )

    fun checkItem(item: ToDoModel, isChecked: Boolean) {
        viewModelScope.launch {
            repository.updateToDoItem(item.copy(isDone = isChecked))
        }
    }
}