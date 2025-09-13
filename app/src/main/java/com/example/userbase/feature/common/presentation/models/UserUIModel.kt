package com.example.userbase.feature.common.presentation.models

data class UserUIModel(
    val id: Int,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val genderUIModel: GenderUIModel
)