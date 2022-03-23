package com.meekoo.todonow.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meekoo.todonow.data.repositories.TodoRepository
import com.meekoo.todonow.domain.Todo
import com.meekoo.todonow.util.AddEditTodoEvents
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
class TodoAddEditViewModel @Inject constructor(
    val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var todo by mutableStateOf<Todo?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvents = Channel<TodoUiEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()

    init {
        savedStateHandle.get<Long>("todoId")?.takeIf { it != -1L }?.let { id ->
            viewModelScope.launch {
                repository.getTodoById(id = id)?.let { cachedTodo ->
                    title = cachedTodo.title.orEmpty()
                    description = cachedTodo.description.orEmpty()
                    this@TodoAddEditViewModel.todo = cachedTodo
                }
            }
        }
    }

    fun sendEvents(events: AddEditTodoEvents) {
        when (events) {
            is AddEditTodoEvents.OnDescriptionChanged -> {
                description = events.description
            }

            AddEditTodoEvents.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if (title.isBlank()) {
                        sendUiEvent(event = TodoUiEvents.ShowSnackbar(message = "Title can't be empty"))
                        return@launch
                    }

                    repository.insertTodo(todo = Todo(
                        id = todo?.id,
                        title = title,
                        description = description,
                        isDone = todo?.isDone ?: false
                    ))

                    sendUiEvent(event = TodoUiEvents.PopBackStack)
                }
            }
            
            is AddEditTodoEvents.OnTitleChanged -> {
                title = events.title
            }
        }
    }

    private fun sendUiEvent(event: TodoUiEvents) {
        viewModelScope.launch {
            _uiEvents.send(event)
        }
    }
}
