package com.example.userbase.feature.usersList.presentation.screens.state

import com.example.userbase.feature.common.presentation.models.UserUIModel

data class UserListUiState(
    val users: List<UserUIModel> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val pagination: PaginationState = PaginationState(),
    val error: String? = null
)