package com.nuncamaria.getthingsdone

import com.nuncamaria.getthingsdone.data.ToDoListRepository
import com.nuncamaria.getthingsdone.fakedata.fakeToDoList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ToDoListRepositoryTest {

    private val repository = ToDoListRepository()

    @Test
    fun data_isSuccess() = runBlocking {

        val actual = repository.getToDoList()

        assertEquals(Result.success(fakeToDoList), actual)
    }
}