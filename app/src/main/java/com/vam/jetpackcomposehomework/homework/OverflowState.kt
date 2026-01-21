package com.vam.jetpackcomposehomework.homework

import java.util.UUID

data class OverflowState(val texts: List<OverflowItem>)

data class OverflowItem(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val isToggled: Boolean = false,
    val isOverflowing: Boolean = false
)