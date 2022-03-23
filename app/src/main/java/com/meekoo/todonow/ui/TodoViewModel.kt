package com.meekoo.todonow.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meekoo.todonow.data.repositories.TodoRepository
import com.meekoo.todonow.domain.Todo
import com.meekoo.todonow.util.Routes
import com.meekoo.todonow.util.TodoEvents
import com.meekoo.todonow.util.TodoUiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ajay Verma on 23/03/22.
 */
@HiltViewModel
class TodoViewModel @Inject constructor(val repository: TodoRepository): ViewModel() {

    private val cachedTodos = repository.getCachedTodos()

    private val _uiState = Channel<TodoUiEvents>()
    val uiStateFlow = _uiState.receiveAsFlow()

    private var deletedTodo: Todo? = null

    fun sendEvents(events: TodoEvents) {
        when(events) {
            TodoEvents.OnAddClick -> {
                sendUiEvents(events = TodoUiEvents.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodoEvents.OnDeleteClick -> {
                viewModelScope.launch {
                    deletedTodo = events.todo
                    repository.deleteTod(todo = events.todo)
                    sendUiEvents(events = TodoUiEvents.ShowSnackbar(message = "Todo deleted successfully", action = "Undo now"))
                }
            }
            is TodoEvents.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertTodo(todo = events.todo.copy(isDone = events.isDone))
                }
            }
            is TodoEvents.OnTodoClick -> {
                sendUiEvents(events = TodoUiEvents.Navigate(route = Routes.ADD_EDIT_TODO + "?todoId=${events.todo.id}"))
            }

            TodoEvents.UnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
        }
    }

    private fun sendUiEvents(events: TodoUiEvents) {
        viewModelScope.launch {
            _uiState.send(events)
        }
    }
}