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

object ValidationManager {

    private val nameValidation = "^[A-Za-zÇĞİÖŞÜçğıöşü\\s'-]+$".toRegex()

    private fun runRules(vararg rules: () -> String): String {
        for (rule in rules) {
            val error = rule()
            if (error.isNotBlank()) return error
        }
        return ""
    }

    fun validateName(name: String): String = runRules(
        { if (name.isBlank()) "Name cannot be blank" else "" },
        { if (name.length < 2) "Name must be at least 2 characters long" else "" },
        { if (name.trim() != name) "Name cannot start or end with a space" else "" },
        { if (!nameValidation.matches(name)) "Name can only contain letters" else "" }
    )

    fun validateSurname(surname: String): String = runRules(
        { if (surname.isBlank()) "Surname cannot be blank" else "" },
        { if (surname.length < 2) "Surname must be at least 2 characters long" else "" },
        { if (surname.trim() != surname) "Surname cannot start or end with a space" else "" },
        { if (!nameValidation.matches(surname)) "Surname can only contain letters" else "" }
    )

    fun validateEmail(email: String): String = runRules(
        { if (email.isBlank()) "Email cannot be blank" else "" },
        {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                    .matches()
            ) "Invalid email address" else ""
        }
    )

    fun validatePassword(password: String): String = runRules(
        { if (password.isBlank()) "Password cannot be blank" else "" },
        { if (password.length < 8) "Password must be at least 8 characters long" else "" }
    )
}