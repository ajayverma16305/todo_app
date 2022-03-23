package com.meekoo.todonow.util

/**
 * Created by Ajay Verma on 23/03/22.
 */
sealed class AddEditTodoEvents {
    data class OnTitleChanged(val title: String) : AddEditTodoEvents()
    data class OnDescriptionChanged(val description: String) : AddEditTodoEvents()
    object OnSaveTodoClick : AddEditTodoEvents()
}
