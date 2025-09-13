package com.example.userbase.feature.usersList.presentation.screens.actions

sealed class UsersListUiActions {
    object GetUsersList : UsersListUiActions()
    object ReloadUsersList : UsersListUiActions()
}