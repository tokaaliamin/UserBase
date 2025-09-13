package com.example.userbase.utils

import com.example.userbase.R

object FormValidator {
    fun validateUsername(username: String): Int? {
        return when {
            username.isBlank() -> R.string.username_is_required
            username.length < 3 -> R.string.username_must_be_at_least_3_characters
            username.length > 20 -> R.string.username_must_not_exceed_20_characters
            !username.matches(Regex("^[a-zA-Z0-9_]+$")) -> R.string.username_can_only_contain_letters_numbers_and_underscores
            else -> null
        }
    }

    fun validateJobTitle(jobTitle: String): Int? {
        return when {
            jobTitle.isBlank() -> R.string.job_title_is_required
            jobTitle.length < 2 -> R.string.job_title_must_be_at_least_2_characters
            jobTitle.length > 50 -> R.string.job_title_must_not_exceed_50_characters
            else -> null
        }
    }

    fun validateAge(age: String): Int? {
        return when {
            age.isBlank() -> R.string.age_is_required
            age.toIntOrNull() == null -> R.string.age_is_required
            age.toInt() == 0 -> R.string.age_cant_be_zero
            else -> null
        }
    }

    fun validateGender(gender: String): Int? {
        return if (gender.isBlank()) R.string.please_select_a_gender else null
    }
}
