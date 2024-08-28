package com.nuncamaria.getthingsdone.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nuncamaria.getthingsdone.data.ToDoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoListDao {

    @Query("SELECT * from items ORDER BY title ASC")
    fun getAllItems(): Flow<List<ToDoModel>>

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<ToDoModel>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ToDoModel)

    @Update
    suspend fun update(item: ToDoModel)

    @Delete
    suspend fun delete(item: ToDoModel)
}