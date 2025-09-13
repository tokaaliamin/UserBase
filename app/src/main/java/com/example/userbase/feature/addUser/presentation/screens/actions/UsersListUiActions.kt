package com.example.userbase.feature.addUser.presentation.screens.actions

sealed class UserFormAction {
    data class UpdateUsername(val username: String) : UserFormAction()
    data class UpdateJobTitle(val jobTitle: String) : UserFormAction()
    data class UpdateAge(val age: String) : UserFormAction()
    data class UpdateGender(val gender: String) : UserFormAction()
    object SubmitUser : UserFormAction()
}
