package com.example.userbase.feature.usersList.domain.usecases

import com.example.userbase.feature.common.domain.models.toUi
import com.example.userbase.feature.common.presentation.models.UserUIModel
import com.example.userbase.feature.usersList.data.repos.UsersListRepo
import javax.inject.Inject

class UsersListUseCase @Inject constructor(private val usersListRepo: UsersListRepo) {

    suspend operator fun invoke(lastUserId: Long?): Result<List<UserUIModel>> {
        return usersListRepo.fetchUsers(lastUserId)
            .map { users -> users.map { user -> user.toUi() } }
    }
}