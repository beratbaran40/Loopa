package com.beratbaran.loopa.ui.categorydetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.beratbaran.loopa.data.repository.PlaceRepository
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.categorydetails.CategoryDetailsContract.UiAction
import com.beratbaran.loopa.ui.categorydetails.CategoryDetailsContract.UiEffect
import com.beratbaran.loopa.ui.categorydetails.CategoryDetailsContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<UiState, UiAction, UiEffect>(
    initialState = UiState()
) {

    private val categoryId: Int = savedStateHandle["categoryId"] ?: 0

    init {
        getCategoryPlaces()
    }

    override fun handleAction(action: UiAction) {
        when (action) {
            is UiAction.OnDetailsClick -> {
                setEffect(UiEffect.NavigateToDetails(action.placeId))
            }

            UiAction.OnToggleFavorite -> {
                setState { copy(isFavorite = !isFavorite) }
            }

            UiAction.OnBackClick -> {
                setEffect(UiEffect.NavigateToBack)
            }
        }
    }

    private fun getCategoryPlaces() = viewModelScope.launch {
        setState { copy(isLoading = true) }

        runCatching {
            placeRepository.getCategoryPlaces(categoryId)
        }.onSuccess { places ->
            setState {
                copy(
                    places = places,
                    numberOfPlaces = places.size,
                    isLoading = false,
                )
            }
        }.onFailure {
            setState {
                copy(
                    isLoading = false,
                )
            }
        }
    }
}