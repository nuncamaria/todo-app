package com.nuncamaria.getthingsdone.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nuncamaria.getthingsdone.GetThingsDoneApplication
import com.nuncamaria.getthingsdone.ui.todoentry.AddToDoViewModel
import com.nuncamaria.getthingsdone.ui.todolist.ToDoListViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEntryViewModel
        initializer {
            AddToDoViewModel(getThingsDoneApplication().container.itemsRepository)
        }

        // Initializer for HomeViewModel
        initializer {
            ToDoListViewModel(
                getThingsDoneApplication().container.getToDoListUseCase,
                getThingsDoneApplication().container.itemsRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [GetThingsDoneApplication].
 */
fun CreationExtras.getThingsDoneApplication(): GetThingsDoneApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GetThingsDoneApplication)