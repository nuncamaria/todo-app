package com.nuncamaria.getthingsdone.domain

import com.nuncamaria.getthingsdone.data.ToDoListRepository
import com.nuncamaria.getthingsdone.data.ToDoModel
import com.nuncamaria.getthingsdone.data.ToDoStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

typealias ToDosLinkedHashMap = LinkedHashMap<ToDoStatus, List<ToDoModel>>

class GetToDoListUseCase(private val repository: ToDoListRepository) {

    operator fun invoke(): Flow<ToDosLinkedHashMap> =
        repository.getAllToDoListStream().map {
            sortToDosByStatus(it)
        }

    private fun sortToDosByStatus(toDos: List<ToDoModel>): ToDosLinkedHashMap {
        // LinkedHashMap preserves the insertion order of entries during the iteration.
        val linkedHashMapTodos = ToDosLinkedHashMap()

        if (toDos.isEmpty()) {
            return linkedHashMapTodos
        }

        linkedHashMapTodos[ToDoStatus.TO_DO] =
            toDos.filter { it.isDone.not() }

        linkedHashMapTodos[ToDoStatus.DONE] =
            toDos.filter { it.isDone }

        return linkedHashMapTodos
    }
}