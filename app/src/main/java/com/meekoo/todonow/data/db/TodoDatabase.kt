package com.meekoo.todonow.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.meekoo.todonow.data.dao.TodoDao
import com.meekoo.todonow.data.entities.TodoEntity

/**
 * Created by Ajay Verma on 23/03/22.
 */
@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao

    companion object {
        const val DATABASE_NAME = "todo_database"
    }
}
