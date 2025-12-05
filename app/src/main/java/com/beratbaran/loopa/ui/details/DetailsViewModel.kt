package com.beratbaran.loopa.ui.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.beratbaran.loopa.data.repository.PlaceRepository
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.details.DetailsContract.UiAction
import com.beratbaran.loopa.ui.details.DetailsContract.UiEffect
import com.beratbaran.loopa.ui.details.DetailsContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
    savedStateHandle: SavedStateHandle,
) :
    BaseViewModel<UiState, UiAction, UiEffect>(
        initialState = UiState()
    ) {

        private val placeId: Int = 1 //savedStateHandle["placeId"] ?: 0

        init {
            Log.d("DetailsViewModel", "placeId from nav: $placeId")
            loadPlaceDetails()
        }

    private fun onImageSelected(index: Int?) {
        setState { copy(selectedIndex = index) }
    }

    private fun loadPlaceDetails() = viewModelScope.launch {
        setState { copy(isLoading = true) }

        runCatching {
            placeRepository.getPlaceDetail(placeId)
        }.onSuccess { detail ->
            Log.d("DetailsViewModel", "detail from repo: $detail")
            setState {
                copy(
                    isLoading = false,
                    placeName = detail.name,
                    placeRating = detail.rating.toString(),
                    placeLocation = detail.location,
                    placeDescription = detail.description,
                    placeCategory = detail.categoryId.toString(),
                    placeCategoryName = detail.categoryName,
                    placeImageUrl = detail.images.firstOrNull().orEmpty(),
                    images = detail.images,
                    isFavorite = detail.isFavorite,
                    selectedIndex = 0
                )
            }
        }.onFailure { error ->
            Log.e("DetailsViewModel", "getPlaceDetail error:", error)
            setState { copy(isLoading = false) }
        }
    }

    override fun handleAction(action: UiAction) {
        when (action) {
            UiAction.ShowOnMapClick -> {
                setEffect(UiEffect.NavigateToMaps)
            }

            UiAction.OnBackClick -> {
                setEffect(UiEffect.NavigateToBack)
            }

            UiAction.ToggleFavorite -> {
                setState { copy(isFavorite = !isFavorite) }
            }

            is UiAction.OnImageSelected -> {
                onImageSelected(action.index)
            }
        }
    }
}