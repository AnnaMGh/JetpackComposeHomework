package com.vam.jetpackcomposehomework.homework

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class ListViewModel : ViewModel() {

    private val _items = MutableStateFlow(DummyData.items)
    val items = _items.asStateFlow()

    val lazyListState = LazyListState()
    val snackbarHostState = SnackbarHostState()

    init {
        snapshotFlow {
            with(lazyListState.layoutInfo) {
                if (totalItemsCount == 0) false
                else ((visibleItemsInfo.lastOrNull()?.index ?: -1) == totalItemsCount - 1)
            }
        }
            .distinctUntilChanged()
            .onEach {
                if (it) {
                    snackbarHostState.showSnackbar(message = "Scrolled to bottom")
                }
            }
            .launchIn(viewModelScope)
    }

}