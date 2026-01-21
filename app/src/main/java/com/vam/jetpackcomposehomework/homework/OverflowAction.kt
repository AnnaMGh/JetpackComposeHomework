package com.vam.jetpackcomposehomework.homework

sealed interface OverflowAction {
    data class ToggleOverflow(val id: String) : OverflowAction
    data class SetOverflowing(val id: String) : OverflowAction
}