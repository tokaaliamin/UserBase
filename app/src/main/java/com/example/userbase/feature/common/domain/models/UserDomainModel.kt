package com.example.userbase.feature.common.domain.models

import com.example.userbase.feature.common.presentation.models.GenderUIModel
import com.example.userbase.feature.common.presentation.models.UserUIModel

data class UserDomainModel(
    val id: Int,
    val name: String,
    val jobTitle: String,
    val age: Int,
    val genderModel: GenderDomainModel
)

fun UserDomainModel.toUi() =
    UserUIModel(id, name, age, jobTitle, GenderUIModel.valueOf(genderModel.name))
