package com.meekoo.todonow.data.repositories

import com.meekoo.todonow.data.dao.TodoDao
import com.meekoo.todonow.domain.Todo
import kotlinx.coroutines.flow.map

/**
 * Created by Ajay Verma on 23/03/22.
 */
class TodoRepositoryImpl(private val todoDao: TodoDao) : TodoRepository {
    override suspend fun insertTodo(todo: Todo) = todoDao.insertTodo(todoEntity = todo.toEntity())
    override suspend fun deleteTod(todo: Todo) = todoDao.deleteTodo(todoEntity = todo.toEntity())
    override suspend fun getTodoById(id: Long) = todoDao.getTodoEntity(id = id)?.toDomain()
    override fun getCachedTodos()= todoDao.getCachedTodos().map { todos -> todos.map { it.toDomain() } }
}
