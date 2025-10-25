package com.beratbaran.loopa.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<E : Any> : ViewModel() {

    protected val _uiEffect = Channel<E>(capacity = Channel.BUFFERED)
    val uiEffect: Flow<E> = _uiEffect.receiveAsFlow()

    protected fun setEffect(effect: E) {
        viewModelScope.launch {
            _uiEffect.send(effect)
        }
    }
}