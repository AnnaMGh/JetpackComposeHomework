package com.vam.jetpackcomposehomework.homework

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OverflowViewModel : ViewModel() {

    private var _state = MutableStateFlow(OverflowState(texts = DummyData.items))
    val state = _state.asStateFlow()

    fun onAction(action: OverflowAction) {
        when (action) {
            is OverflowAction.ToggleOverflow -> {
                _state.update { current ->
                    val updatedText = current.texts.map { text ->
                        if (text.id == action.id) text.copy(isToggled = !text.isToggled)
                        else text
                    }
                    current.copy(texts = updatedText)
                }
            }

            is OverflowAction.SetOverflowing -> {
                _state.update { current ->
                    val updatedText = current.texts.map { text ->
                        if (text.id == action.id) text.copy(isOverflowing = true)
                        else text
                    }
                    current.copy(texts = updatedText)
                }
            }
        }
    }
}