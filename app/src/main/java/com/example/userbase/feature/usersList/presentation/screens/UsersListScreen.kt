package com.example.userbase.feature.usersList.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.userbase.feature.usersList.presentation.models.Gender
import com.example.userbase.feature.usersList.presentation.models.UserUIModel
import com.example.userbase.feature.usersList.presentation.screens.components.UserItem
import com.example.userbase.ui.theme.LocalDimensions
import com.example.userbase.ui.theme.UserBaseTheme


@Composable
fun UsersListScreen(
    users: List<UserUIModel>,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val dimens = LocalDimensions.current
    LazyColumn(
        contentPadding = innerPadding,
        modifier = modifier.padding(dimens.large),
        verticalArrangement = Arrangement.spacedBy(dimens.large)
    ) {
        items(items = users) { user ->
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
                gender = Gender.Female
            ), UserUIModel(
                1, "John Doe", age = 25, jobTitle = "Software Engineer",
                gender = Gender.Male
            ), UserUIModel(
                1, "John Doe", age = 20, jobTitle = "Software Engineer",
                gender = Gender.Male
            ), UserUIModel(
                1, "John Doe", age = 20, jobTitle = "Software Engineer",
                gender = Gender.Male
            )
        )

        UsersListScreen(
            users = users,
            innerPadding = PaddingValues(bottom = 80.dp)
        )
    }
}