package com.beratbaran.loopa.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> Flow<T>.CollectWithLifecycle(
    collect: suspend (T) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(this, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@CollectWithLifecycle.collect { effect ->
                collect(effect)
            }
        }
    }
}

fun String.validateName(): String {
    val regex = "^[A-Za-zÇĞİÖŞÜçğıöşü\\s'-]+$".toRegex()
    return when {
        isBlank() -> "Name cannot be blank"
        length < 2 -> "Name must be at least 2 characters long"
        trim() != this -> "Name cannot start or end with a space"
        !regex.matches(this) -> "Name can only contain letters"
        else -> ""
    }
}

fun String.validateSurname(): String {
    val regex = "^[A-Za-zÇĞİÖŞÜçğıöşü\\s'-]+$".toRegex()
    return when {
        isBlank() -> "Surname cannot be blank"
        length < 2 -> "Surname must be at least 2 characters long"
        trim() != this -> "Surname cannot start or end with a space"
        !regex.matches(this) -> "Surname can only contain letters"
        else -> ""
    }
}

fun String.validateEmail(): String = when {
    this.isBlank() -> "Email cannot be blank"
    !android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches() -> ("Invalid email address")
    else -> ""
}

fun String.validatePassword(): String {
    return when {
        this.isBlank() -> "Password cannot be blank"
        length < 8 -> "Password must be at least 8 characters long"
        else -> ""
    }
}