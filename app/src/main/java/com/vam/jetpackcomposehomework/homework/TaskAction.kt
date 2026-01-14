package com.vam.jetpackcomposehomework.homework

sealed interface TaskAction {
    data class OnTaskChecked(val id: String) : TaskAction
    data class OnTaskDeleted(val id: String) : TaskAction
    data class OnNewTaskTitleChanged(val title: String) : TaskAction
    data class OnNewTaskDescriptionChanged(val description: String) : TaskAction
    data object OnNewTaskAdded : TaskAction
}