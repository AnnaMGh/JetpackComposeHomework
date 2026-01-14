package com.vam.jetpackcomposehomework.homework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private var _state = MutableStateFlow(TaskState(tasks = DummyData.tasks))
    val state = _state.asStateFlow()

    private val _events = Channel<TaskEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: TaskAction) {
        when (action) {
            is TaskAction.OnTaskChecked -> {
                _state.update { current ->
                    val updatedTasks = current.tasks.map { task ->
                        if (task.id == action.id) task.copy(isChecked = !task.isChecked)
                        else task
                    }
                    current.copy(tasks = updatedTasks)
                }
            }

            is TaskAction.OnTaskDeleted -> _state.update { current ->
                val updatedTasks = current.tasks.filter { it.id != action.id }
                current.copy(tasks = updatedTasks)
            }

            is TaskAction.OnNewTaskTitleChanged -> _state.update { current ->
                current.copy(newTask = current.newTask.copy(title = action.title))
            }

            is TaskAction.OnNewTaskDescriptionChanged -> _state.update { current ->
                current.copy(newTask = current.newTask.copy(description = action.description))
            }

            is TaskAction.OnNewTaskAdded -> {
                _state.update { current ->
                    if (current.newTask.title.trim().isEmpty()) {
                        viewModelScope.launch { _events.send(TaskEvent.Error("Missing title")) }
                        return
                    }

                    if (current.newTask.description.trim().isEmpty()) {
                        viewModelScope.launch { _events.send(TaskEvent.Error("Missing description")) }
                        return
                    }

                    val updatedTasks = current.tasks + current.newTask
                    current.copy(newTask = Task(), tasks = updatedTasks)
                }
            }
        }
    }
}