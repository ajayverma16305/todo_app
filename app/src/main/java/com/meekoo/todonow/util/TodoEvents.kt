package com.meekoo.todonow.util

import com.meekoo.todonow.domain.Todo

/**
 * Created by Ajay Verma on 23/03/22.
 */
sealed class TodoEvents {
    data class OnDeleteClick(val todo: Todo) : TodoEvents()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean) : TodoEvents()
    object UnUndoDeleteClick : TodoEvents()
    data class OnTodoClick(val todo: Todo) : TodoEvents()
    object OnAddClick : TodoEvents()
}
