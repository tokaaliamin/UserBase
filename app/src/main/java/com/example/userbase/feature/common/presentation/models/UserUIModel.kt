package com.example.userbase.feature.common.presentation.models

data class UserUIModel(
    val id: Long,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val genderUIModel: GenderUIModel
)