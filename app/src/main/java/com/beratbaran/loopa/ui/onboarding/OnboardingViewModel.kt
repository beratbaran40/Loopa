package com.beratbaran.loopa.ui.onboarding

import androidx.lifecycle.viewModelScope
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.onboarding.OnboardingContract.UiAction
import com.beratbaran.loopa.ui.onboarding.OnboardingContract.UiEffect
import com.beratbaran.loopa.ui.onboarding.OnboardingContract.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel : BaseViewModel<UiEffect>() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                delay(2000)
                _uiState.update { it.copy(bgIndex = (it.bgIndex + 1) % 4) }
            }
        }
    }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnLoginClick -> setEffect(UiEffect.NavigateToLogin)
            UiAction.OnRegisterClick -> setEffect(UiEffect.NavigateToRegister)
        }
    }
}