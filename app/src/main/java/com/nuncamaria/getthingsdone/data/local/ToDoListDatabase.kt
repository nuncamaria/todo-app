package com.nuncamaria.getthingsdone.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nuncamaria.getthingsdone.data.ToDoModel

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [ToDoModel::class], version = 1, exportSchema = false)
abstract class ToDoListDatabase : RoomDatabase() {
    abstract fun toDoListDao(): ToDoListDao

    companion object {
        @Volatile
        private var Instance: ToDoListDatabase? = null

        fun getDatabase(ctx: Context): ToDoListDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(ctx, ToDoListDatabase::class.java, "todolist_database")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}