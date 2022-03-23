package com.meekoo.todonow.data.dao

import androidx.room.*
import com.meekoo.todonow.data.entities.TodoEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ajay Verma on 23/03/22.
 */
@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoEntity: TodoEntity): Long

    @Delete
    suspend fun deleteTodo(todoEntity: TodoEntity): Int

    @Query("SELECT * FROM TodoEntity WHERE id = :id")
    suspend fun getTodoEntity(id: Long): TodoEntity?

    @Query("SELECT * FROM TodoEntity")
    fun getCachedTodos(): Flow<List<TodoEntity>>
}