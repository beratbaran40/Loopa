package com.beratbaran.loopa.common.util

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

    fun computePasswordStrength(password: String): PasswordStrength? {
        if (password.isBlank()) return null

        var score = 0
        val length = password.length
        val hasLower = password.any { it.isLowerCase() }
        val hasUpper = password.any { it.isUpperCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecial = password.any { !it.isLetterOrDigit() }

        score += when {
            length >= 12 -> 2
            length >= 8 -> 1
            else -> 0
        }

        if (hasLower) score++
        if (hasUpper) score++
        if (hasDigit) score++
        if (hasSpecial) score++

        return when {
            score >= 5 -> PasswordStrength.STRONG
            score >= 3 -> PasswordStrength.MEDIUM
            else -> PasswordStrength.WEAK
        }
    }
}

enum class PasswordStrength { STRONG, MEDIUM, WEAK }