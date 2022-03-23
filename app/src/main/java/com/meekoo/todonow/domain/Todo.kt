package com.meekoo.todonow.domain

import com.meekoo.todonow.data.entities.TodoEntity

/**
 * Created by Ajay Verma on 23/03/22.
 */
data class Todo(
    val id: Long? = null,
    val title: String?,
    val description: String?,
    val isDone: Boolean
) {
    fun toEntity() : TodoEntity {
        return TodoEntity(id = id, title = title, description = description, isDone = isDone)
    }
}