package com.example.userbase.feature.common.domain.models

import com.example.userbase.feature.common.data.models.GenderDataModel
import com.example.userbase.feature.common.data.models.UserDateModel
import com.example.userbase.feature.common.presentation.models.GenderUIModel
import com.example.userbase.feature.common.presentation.models.UserUIModel

data class UserDomainModel(
    val id: Long? = null,
    val name: String,
    val jobTitle: String,
    val age: Int,
    val genderModel: GenderDomainModel
)

fun UserDomainModel.toUi() =
    UserUIModel(id ?: 0, name, age, jobTitle, GenderUIModel.valueOf(genderModel.name))

fun UserDomainModel.toData() =
    UserDateModel(id, name, jobTitle, age, GenderDataModel.valueOf(genderModel.name))
