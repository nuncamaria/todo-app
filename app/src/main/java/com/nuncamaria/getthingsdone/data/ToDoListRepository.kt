package com.nuncamaria.getthingsdone.data

import com.nuncamaria.getthingsdone.data.local.ToDoListDao
import kotlinx.coroutines.flow.Flow

class ToDoListRepository(private val dao: ToDoListDao) {

    fun getAllToDoListStream(): Flow<List<ToDoModel>> = dao.getAllItems()

    fun getToDoItemStream(id: Int): Flow<ToDoModel?> = dao.getItem(id)

    suspend fun insertToDoItem(item: ToDoModel) = dao.insert(item)

    suspend fun updateToDoItem(item: ToDoModel) = dao.update(item)

    suspend fun deleteToDoItem(item: ToDoModel) = dao.delete(item)
}