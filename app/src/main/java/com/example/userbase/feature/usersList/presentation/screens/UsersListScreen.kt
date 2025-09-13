package com.example.userbase.feature.usersList.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.userbase.R
import com.example.userbase.feature.usersList.presentation.screens.actions.UsersListUiActions
import com.example.userbase.feature.usersList.presentation.screens.components.AddUserButton
import com.example.userbase.feature.usersList.presentation.screens.components.UserItem
import com.example.userbase.feature.usersList.presentation.screens.state.UserListUiState
import com.example.userbase.feature.usersList.presentation.viewmodels.UsersListViewModel
import com.example.userbase.ui.theme.LocalDimensions
import com.example.userbase.ui.theme.UserBaseTheme

@Composable
fun UsersListScreen(usersListViewModel: UsersListViewModel = hiltViewModel()) {
    val uiState by usersListViewModel.uiState.collectAsStateWithLifecycle()
    usersListViewModel.onAction(UsersListUiActions.GetUsersList)
    UserBaseTheme {
        Scaffold(
            floatingActionButton = { AddUserButton({}) },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            UsersListContent(innerPadding, uiState)
        }
    }
}

@Composable
fun UsersListContent(
    innerPadding: PaddingValues,
    uiState: UserListUiState,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimensions.current
    if (uiState.users.isEmpty())
        UsersListPlaceHolder()
    if (uiState.error != null)
        UsersListError(uiState.error)
    else {
        LazyColumn(
            contentPadding = innerPadding,
            modifier = modifier.padding(dimens.large),
            verticalArrangement = Arrangement.spacedBy(dimens.large)
        ) {
            items(items = uiState.users) { user ->
                UserItem(user)
            }
        }
    }
}

@Composable
fun UsersListPlaceHolder() {
    Box {
        Text(
            text = stringResource(R.string.no_users_found),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun UsersListError(errorMessage: String) {
    Box {
        Text(
            text = errorMessage,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}