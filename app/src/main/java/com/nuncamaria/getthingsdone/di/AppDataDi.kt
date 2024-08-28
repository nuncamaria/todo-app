package com.nuncamaria.getthingsdone.di

import android.content.Context
import com.nuncamaria.getthingsdone.data.ToDoListRepository
import com.nuncamaria.getthingsdone.data.local.ToDoListDatabase
import com.nuncamaria.getthingsdone.domain.GetToDoListUseCase

/**
 * App container for Dependency injection.
 */
interface AppDi {
    val itemsRepository: ToDoListRepository
    val getToDoListUseCase: GetToDoListUseCase
}

/**
 * [AppDataDi] implementation that provides instance of [ToDoListRepository]
 */
class AppDataDi(private val ctx: Context) : AppDi {
    /**
     * Implementation for [ToDoListRepository]
     */
    override val itemsRepository by lazy {
        ToDoListRepository(ToDoListDatabase.getDatabase(ctx).toDoListDao())
    }

    override val getToDoListUseCase by lazy {
        GetToDoListUseCase(itemsRepository)
    }
}