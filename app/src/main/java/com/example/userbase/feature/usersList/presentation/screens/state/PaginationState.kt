package com.example.userbase.feature.usersList.presentation.screens.state

data class PaginationState(
    val currentPage: Int = 0,
    var lastUserId: Long? = null,
    val hasMorePages: Boolean = true,
    val pageSize: Int = 15
)