package com.beratbaran.loopa.domain

import com.beratbaran.loopa.ui.homepage.PlaceModel

data class PlaceResponseModel(
    val topPlaces: List<PlaceModel>,
    val wantToLookPlaces: List<PlaceModel>,
)
