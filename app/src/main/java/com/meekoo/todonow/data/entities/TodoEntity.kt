package com.meekoo.todonow.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.meekoo.todonow.domain.Todo

/**
 * Created by Ajay Verma on 23/03/22.
 */
@Entity
data class TodoEntity(
    @PrimaryKey
    val id: Long? = null,
    val title: String? = null,
    val description: String? = null,
    val isDone: Boolean = false
) {
    fun toDomain(): Todo {
        return Todo(id = id, title = title, description = description, isDone = isDone)
    }
}
