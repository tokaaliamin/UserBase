package com.example.userbase.feature.addUser.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userbase.feature.addUser.domain.usecases.UserFormUseCase
import com.example.userbase.feature.addUser.presentation.screens.actions.UserFormAction
import com.example.userbase.feature.addUser.presentation.screens.state.UserFormState
import com.example.userbase.feature.addUser.presentation.screens.state.toDomain
import com.example.userbase.utils.FormValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFormViewModel @Inject constructor(private val useCase: UserFormUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(UserFormState())
    val uiState: StateFlow<UserFormState> = _uiState

    fun onAction(action: UserFormAction) {
        when (action) {
            is UserFormAction.UpdateUsername -> {
                val usernameError = FormValidator.validateUsername(_uiState.value.username)
                _uiState.update { state ->
                    state.copy(
                        username = action.username,
                        usernameErrorId = usernameError
                    )
                }
            }

            is UserFormAction.UpdateJobTitle -> {
                val jobTitleError = FormValidator.validateJobTitle(_uiState.value.jobTitle)

                _uiState.update { state ->
                    state.copy(
                        jobTitle = action.jobTitle,
                        jobTitleErrorId = jobTitleError
                    )
                }
            }

            is UserFormAction.UpdateAge -> {
                val ageError = FormValidator.validateAge(_uiState.value.age)

                _uiState.update { state ->
                    state.copy(
                        age = action.age,
                        ageErrorId = ageError
                    )
                }
            }

            is UserFormAction.UpdateGender -> {
                val genderError = FormValidator.validateGender(_uiState.value.gender.orEmpty())
                _uiState.update { state ->
                    state.copy(
                        gender = action.gender,
                        genderErrorId = genderError
                    )
                }
            }

            is UserFormAction.SubmitUser -> {
                val usernameError = FormValidator.validateUsername(_uiState.value.username)
                val jobTitleError = FormValidator.validateJobTitle(_uiState.value.jobTitle)
                val ageError = FormValidator.validateAge(_uiState.value.age)
                val genderError = FormValidator.validateGender(_uiState.value.gender.orEmpty())

                _uiState.update { state ->
                    state.copy(
                        usernameErrorId = usernameError,
                        jobTitleErrorId = jobTitleError,
                        ageErrorId = ageError,
                        genderErrorId = genderError,
                    )
                }

                if (usernameError == null && jobTitleError == null && ageError == null && genderError == null)
                    addUser()
            }

        }
    }


    private fun addUser() {
        viewModelScope.launch {
            val result = useCase.invoke(uiState.value.toDomain())
            _uiState.update { it.copy() }
            handleAddUsersResult(result)
        }
    }

    private fun handleAddUsersResult(result: Result<Unit>) {
        when {
            result.isSuccess -> {
                _uiState.update { it.copy(addedSuccessfully = true) }
            }

            result.isFailure -> {
                result.exceptionOrNull()?.let { throwable ->
                    _uiState.update {
                        it.copy(error = throwable.message)
                    }
                }

            }
        }

    }
}