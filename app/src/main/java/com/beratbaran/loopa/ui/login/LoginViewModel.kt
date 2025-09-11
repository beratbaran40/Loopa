package com.beratbaran.loopa.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beratbaran.loopa.ui.login.LoginContract.UiAction
import com.beratbaran.loopa.ui.login.LoginContract.UiEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        LoginContract.UiState(
            isLoading = false,
            errorMessages = null
        )
    )
    val uiState: StateFlow<LoginContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<UiEffect>(
        replay = 0,
        extraBufferCapacity = 1
    )
    val uiEffect: Flow<UiEffect> = _uiEffect

    init {
        viewModelScope.launch {
            while (isActive) {
                delay(2000)
                _uiState.update { it.copy(bgIndex = (it.bgIndex + 1) % 4) }
            }
        }
    }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.ClickedToLogin -> _uiEffect.tryEmit(UiEffect.NavigateToHomePage)
            is UiAction.EmailChanged -> _uiState.update {
                it.copy(email = uiAction.email)
            }

            is UiAction.PasswordChanged -> _uiState.update {
                it.copy(password = uiAction.password)
            }
        }
    }
}
