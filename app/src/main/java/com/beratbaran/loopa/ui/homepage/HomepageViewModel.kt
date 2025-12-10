package com.beratbaran.loopa.ui.homepage

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.beratbaran.loopa.data.repository.PlaceRepository
import com.beratbaran.loopa.domain.repository.UserRepository
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.homepage.HomepageContract.UiAction
import com.beratbaran.loopa.ui.homepage.HomepageContract.UiEffect
import com.beratbaran.loopa.ui.homepage.HomepageContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
    private val userRepository: UserRepository
) : BaseViewModel<UiState, UiAction, UiEffect>(
    initialState = UiState()
) {

    init {
        loadSaluteName()
        getHomePagePlaces()
        Log.d("HomepageViewModel", "init")
    }

    override fun handleAction(action: UiAction) {
        when (action) {
            is UiAction.OnDetailsClick -> setEffect(UiEffect.NavigateToDetails(action.placeId))
            UiAction.ToggleFavorite -> setEffect(UiEffect.NavigateToFavorites)
        }
    }

    private fun getHomePagePlaces() = viewModelScope.launch {
        Log.d("HomepageViewModel", "getHomePagePlaces")
        setState { copy(isLoading = true) }

        runCatching {
            placeRepository.getPlaces()
        }.onSuccess { places ->
            Log.d("HomepageViewModel", "onSuccess")
            Log.d("HomepageViewModel", "onSuccess top = ${places.topPlaces.size}")
            Log.d("HomepageViewModel", "onSuccess want = ${places.wantToLookPlaces.size}")
            setState {
                copy(
                    isLoading = false,
                    topPlaces = places.topPlaces,
                    wantToLookPlaces = places.wantToLookPlaces,
                    error = null
                )
            }
        }.onFailure { throwable ->
            Log.e("HomepageViewModel", "Error: ${throwable.message}")
            setState { copy(isLoading = false, error = throwable.message ?: "Something went wrong :(") }
        }
    }

    private fun loadSaluteName(){
        viewModelScope.launch {
            userRepository.loadCurrentUser()
                .onSuccess { user ->
                    setState { copy(name = user.name) }
                }
        }
    }
}