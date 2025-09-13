package com.example.userbase.feature.usersList.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.userbase.feature.common.presentation.models.GenderUIModel
import com.example.userbase.feature.common.presentation.models.UserUIModel
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
    val users = listOf(
        UserUIModel(
            1, "Jane Doe", age = 29, jobTitle = "Teacher",
            genderUIModel = GenderUIModel.FEMALE
        ), UserUIModel(
            1, "John Doe", age = 25, jobTitle = "Software Engineer",
            genderUIModel = GenderUIModel.MALE
        ), UserUIModel(
            1, "John Doe", age = 20, jobTitle = "Software Engineer",
            genderUIModel = GenderUIModel.MALE
        ), UserUIModel(
            1, "John Doe", age = 20, jobTitle = "Software Engineer",
            genderUIModel = GenderUIModel.MALE
        )
    )

    val dimens = LocalDimensions.current
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

@Preview(showBackground = true)
@Composable
fun UsersListScreenPreview() {
    UserBaseTheme {
        val users = listOf(
            UserUIModel(
                1, "Jane Doe", age = 29, jobTitle = "Teacher",
                genderUIModel = GenderUIModel.FEMALE
            ), UserUIModel(
                1, "John Doe", age = 25, jobTitle = "Software Engineer",
                genderUIModel = GenderUIModel.MALE
            ), UserUIModel(
                1, "John Doe", age = 20, jobTitle = "Software Engineer",
                genderUIModel = GenderUIModel.MALE
            ), UserUIModel(
                1, "John Doe", age = 20, jobTitle = "Software Engineer",
                genderUIModel = GenderUIModel.MALE
            )
        )

        UsersListScreen()
    }
}