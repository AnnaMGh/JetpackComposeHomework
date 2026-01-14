package com.vam.jetpackcomposehomework.homework

import java.util.UUID

data class TaskState(
    val newTask: Task = Task(),
    val tasks: List<Task> = listOf()
)

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val description: String = "",
    val isChecked: Boolean = false
)