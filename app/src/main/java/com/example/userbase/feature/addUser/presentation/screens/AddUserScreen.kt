package com.example.userbase.feature.addUser.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.userbase.R
import com.example.userbase.feature.addUser.presentation.screens.actions.UserFormAction
import com.example.userbase.feature.addUser.presentation.screens.state.UserFormState
import com.example.userbase.feature.addUser.presentation.viewmodels.UserFormViewModel
import com.example.userbase.ui.theme.LocalDimensions
import com.example.userbase.ui.theme.UserBaseTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
@RootNavGraph
fun AddUserScreen(
    userFormViewModel: UserFormViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by userFormViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.addedSuccessfully) {
        println(uiState.addedSuccessfully)
        if (uiState.addedSuccessfully)
            navigator.navigateUp()
    }
    UserBaseTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.add_user)) },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            AddUserContent(
                uiState,
                onAction = { action -> userFormViewModel.onAction(action) },
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Composable
fun AddUserContent(
    state: UserFormState,
    onAction: (UserFormAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val dimens = LocalDimensions.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimens.large)
    ) {
        OutlinedTextField(
            value = state.username,
            onValueChange = {
                onAction(UserFormAction.UpdateUsername(it))
            },
            label = { Text(stringResource(R.string.username)) },
            placeholder = { Text(stringResource(R.string.enter_your_username)) },
            isError = state.usernameErrorId != null,
            supportingText = {
                if (state.usernameErrorId != null) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(state.usernameErrorId),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = state.jobTitle,
            onValueChange = {
                onAction(UserFormAction.UpdateJobTitle(it))
            },
            label = { Text(stringResource(R.string.job_title)) },
            placeholder = { Text(stringResource(R.string.enter_your_job_title)) },
            isError = state.jobTitleErrorId != null,
            supportingText = {
                if (state.jobTitleErrorId != null) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(state.jobTitleErrorId),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = state.age,
            onValueChange = {
                onAction(UserFormAction.UpdateAge(it))
            },
            label = { Text(stringResource(R.string.age)) },
            placeholder = { Text(stringResource(R.string.enter_your_age)) },
            isError = state.ageErrorId != null,
            supportingText = {
                if (state.ageErrorId != null) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(state.ageErrorId),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Box {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                enabled = false,
                value = state.gender ?: stringResource(R.string.please_select_a_gender),
                onValueChange = {
                    onAction(UserFormAction.UpdateGender(it))
                },
                label = { Text(stringResource(R.string.gender)) },
                placeholder = { Text(stringResource(R.string.please_select_a_gender)) },
                isError = state.genderErrorId != null,
                supportingText = {
                    if (state.genderErrorId != null) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(state.genderErrorId),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                singleLine = true
            )
            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = !expanded }) {
                DropdownMenuItem(text = { Text(stringResource(R.string.male)) }, onClick = {
                    onAction(UserFormAction.UpdateGender("MALE"))
                    expanded = false
                })
                DropdownMenuItem(text = { Text(stringResource(R.string.female)) }, onClick = {
                    onAction(UserFormAction.UpdateGender("FEMALE"))
                    expanded = false
                })
            }
        }

        ElevatedButton(onClick = { onAction(UserFormAction.SubmitUser) }) {
            Text(text = stringResource(R.string.add))
        }


    }
}
