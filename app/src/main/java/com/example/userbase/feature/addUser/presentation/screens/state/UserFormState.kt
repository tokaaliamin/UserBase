package com.example.userbase.feature.addUser.presentation.screens.state

import com.example.userbase.feature.common.domain.models.GenderDomainModel
import com.example.userbase.feature.common.domain.models.UserDomainModel

data class UserFormState(
    val username: String = "",
    val jobTitle: String = "",
    val age: String = "",
    val gender: String? = null,
    val usernameErrorId: Int? = null,
    val jobTitleErrorId: Int? = null,
    val ageErrorId: Int? = null,
    val genderErrorId: Int? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val addedSuccessfully: Boolean = false
)

fun UserFormState.toDomain() =
    UserDomainModel(
        name = username,
        jobTitle = jobTitle,
        age = age.toInt(),
        genderModel = GenderDomainModel.valueOf(gender.orEmpty())
    )
