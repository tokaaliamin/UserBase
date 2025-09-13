package com.example.userbase.feature.usersList.presentation.models

data class UserUIModel(
    val id: Int,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val gender: Gender
)
