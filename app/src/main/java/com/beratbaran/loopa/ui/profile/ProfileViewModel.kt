package com.beratbaran.loopa.ui.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.beratbaran.loopa.domain.repository.UserRepository
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.profile.ProfileContract.UiAction
import com.beratbaran.loopa.ui.profile.ProfileContract.UiEffect
import com.beratbaran.loopa.ui.profile.ProfileContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel<UiState, UiAction, UiEffect>(
    initialState = UiState()
) {
    init {
        loadProfile()
    }

    override fun handleAction(action: UiAction) {
        when (action) {

            UiAction.OnLogoutClick -> setState { copy(showLogoutDialog = true) }

            UiAction.OnLogoutDismissClick -> setState { copy(showLogoutDialog = false) }

            UiAction.OnEditPasswordClick -> setEffect(UiEffect.NavigateToChangePassword)

            UiAction.OnLogoutConfirmClick -> setEffect(UiEffect.NavigateToOnboarding)
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            userRepository.loadCurrentUser()
                .onSuccess { user ->
                    setState {
                        copy(
                            fullName = user.fullName,
                            name = user.name,
                            surname = user.surname,
                            email = user.email,
                            areFieldsEmpty = false
                        )
                    }
                }
                .onFailure { e ->
                    Log.e("ProfileViewModel", "loadProfile error", e)
                }
        }
    }
}
