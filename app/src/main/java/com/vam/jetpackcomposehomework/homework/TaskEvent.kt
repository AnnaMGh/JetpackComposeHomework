package com.vam.jetpackcomposehomework.homework

sealed interface TaskEvent {
    data class Error(val error: String) : TaskEvent
}