package com.beratbaran.loopa.navigation

sealed class Route(val path: String) {
    data object Onboarding : Route("onboarding")
    data object Login : Route("login")
    data object Register : Route("register")
}