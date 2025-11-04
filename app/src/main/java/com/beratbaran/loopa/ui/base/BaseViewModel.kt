package com.beratbaran.loopa.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : Any, A : Any, E : Any>(initialState: S) : ViewModel() {
    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()
    val currentUiState: S
        get() = _uiState.value

    protected fun setState(reducer: S.() -> S) {
        _uiState.update { it.reducer() }
    }

    private val _uiAction = MutableSharedFlow<A>()
    val uiAction: MutableSharedFlow<A> = _uiAction

    init {
        viewModelScope.launch {
            _uiAction.collect { handleAction(it) }
        }
    }

    protected abstract suspend fun handleAction(action: A)

    private val _uiEffect = Channel<E>(capacity = Channel.BUFFERED)
    val uiEffect: Flow<E> = _uiEffect.receiveAsFlow()

    protected fun setEffect(effect: E) {
        viewModelScope.launch {
            _uiEffect.send(effect)
        }
    }
}