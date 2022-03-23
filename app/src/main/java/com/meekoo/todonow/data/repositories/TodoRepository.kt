package com.meekoo.todonow.data.repositories

import com.meekoo.todonow.domain.Todo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ajay Verma on 23/03/22.
 */
interface TodoRepository {
    suspend fun insertTodo(todo: Todo): Long
    suspend fun deleteTod(todo: Todo): Int
    suspend fun getTodoById(id: Long): Todo?
    fun getCachedTodos(): Flow<List<Todo>>
}