package com.example.userbase.feature.usersList.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.userbase.R
import com.example.userbase.feature.usersList.presentation.models.Gender
import com.example.userbase.feature.usersList.presentation.models.UserUIModel
import com.example.userbase.ui.theme.LocalDimensions
import com.example.userbase.ui.theme.UserBaseTheme

@Composable
fun UserItem(user: UserUIModel, modifier: Modifier = Modifier) {
    val dimens = LocalDimensions.current
    Card(
        elevation = CardDefaults.cardElevation(dimens.medium),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(dimens.medium)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimens.medium)
            ) {
                GenderIcon(user.gender)
                UserName(user.name)
            }
            Spacer(modifier = Modifier.height(dimens.small))
            JobTitle(user.jobTitle)
            Age(user.age)
        }
    }
}

@Composable
fun JobTitle(jobTitle: String, modifier: Modifier = Modifier) {
    val dimens = LocalDimensions.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimens.medium)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_job),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
            modifier = modifier.size(dimens.large)
        )
        Text(
            text = jobTitle,
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier.fillMaxWidth(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun Age(age: Int, modifier: Modifier = Modifier) {
    val dimens = LocalDimensions.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimens.medium)
    ) {
        Image(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
            modifier = modifier.size(dimens.large)
        )
        Text(
            text = stringResource(R.string.years, age),
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier
        )
    }
}


@Composable
fun GenderIcon(gender: Gender, modifier: Modifier = Modifier) {
    val dimens = LocalDimensions.current

    val icon = when (gender) {
        is Gender.Female -> R.drawable.ic_female
        is Gender.Male -> R.drawable.ic_male
    }

    Image(
        painter = painterResource(id = icon),
        contentDescription = null,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        modifier = modifier.size(dimens.large)
    )
}

@Composable
fun UserName(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun UsersListScreenPreview() {
    UserBaseTheme {
        UserItem(
            user = UserUIModel(
                1, "John Doe", age = 20, jobTitle = "Software Engineer",
                gender = Gender.Male
            )
        )
    }
}