package com.beratbaran.loopa.data.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
)
