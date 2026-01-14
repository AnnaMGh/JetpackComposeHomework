package com.vam.jetpackcomposehomework.homework

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel : ViewModel() {

    private var _state = MutableStateFlow(TaskState(tasks = DummyData.tasks))
    val state = _state.asStateFlow()

    fun onAction(action: TaskAction) {
        when (action) {
            is TaskAction.OnItemTaskChecked -> {
                _state.update { current ->
                    val updatedTasks = current.tasks.map { task ->
                        if (task.id == action.id) task.copy(isChecked = !task.isChecked)
                        else task
                    }
                    current.copy(tasks = updatedTasks)
                }
            }
        }
    }
}