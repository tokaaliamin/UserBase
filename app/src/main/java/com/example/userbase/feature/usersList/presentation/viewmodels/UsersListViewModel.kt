package com.example.userbase.feature.usersList.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userbase.feature.common.presentation.models.UserUIModel
import com.example.userbase.feature.usersList.domain.usecases.UsersListUseCase
import com.example.userbase.feature.usersList.presentation.screens.actions.UsersListUiActions
import com.example.userbase.feature.usersList.presentation.screens.state.UserListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(private val useCase: UsersListUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(UserListUiState())
    val uiState: StateFlow<UserListUiState> = _uiState

    fun onAction(action: UsersListUiActions) {
        when (action) {
            is UsersListUiActions.GetUsersList -> {
                _uiState.update { it.copy(isLoading = true) }
                getUsersList()
            }

            is UsersListUiActions.ReloadUsersList -> {
                _uiState.update { state ->
                    state.copy(
                        isLoading = true,
                        pagination = state.pagination.copy(lastUserId = null)
                    )
                }
                getUsersList()
            }
        }
    }

    private fun getUsersList() {
        viewModelScope.launch {
            val result = useCase.invoke(uiState.value.pagination.lastUserId)
            _uiState.update { it.copy(isLoading = false) }
            handleUsersListResult(result)
        }
    }

    private fun handleUsersListResult(result: Result<List<UserUIModel>>) {
        when {
            result.isSuccess -> {
                result.getOrNull()?.apply {
                    _uiState.update {
                        it.copy(users = this, error = null, isLoading = false)
                    }
                }
            }

            result.isFailure -> {
                result.exceptionOrNull()?.let { throwable ->
                    _uiState.update {
                        it.copy(error = throwable.message, isLoading = false)
                    }
                }

            }
        }

    }
}