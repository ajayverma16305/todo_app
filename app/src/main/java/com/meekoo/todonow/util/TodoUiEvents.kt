package com.meekoo.todonow.util

/**
 * Created by Ajay Verma on 23/03/22.
 */
sealed class TodoUiEvents {
    object PopBackStack: TodoUiEvents()
    data class Navigate(val route: String): TodoUiEvents()
    data class ShowSnackbar(val message: String, val action: String? = null): TodoUiEvents()
}