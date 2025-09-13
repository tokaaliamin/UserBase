package com.example.userbase.feature.usersList.presentation.models

sealed class Gender {
    data object Male : Gender()
    data object Female : Gender()
}