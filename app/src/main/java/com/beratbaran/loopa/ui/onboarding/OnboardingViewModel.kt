package com.beratbaran.loopa.ui.onboarding

import androidx.lifecycle.viewModelScope
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.onboarding.OnboardingContract.UiAction
import com.beratbaran.loopa.ui.onboarding.OnboardingContract.UiEffect
import com.beratbaran.loopa.ui.onboarding.OnboardingContract.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OnboardingViewModel : BaseViewModel<UiState, UiEffect>(
    initialState = UiState()
) {
    init {
        viewModelScope.launch {
            while (true) {
                delay(2000)
                setState { copy(bgIndex = (bgIndex + 1) % 4) }
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