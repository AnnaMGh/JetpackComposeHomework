package com.vam.jetpackcomposehomework.homework

sealed interface TaskAction {
    data class OnItemTaskChecked(val id: String) : TaskAction
}