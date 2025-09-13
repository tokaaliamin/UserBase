package com.example.userbase.feature.usersList.data.repos

import com.example.userbase.feature.common.data.models.toDomain
import com.example.userbase.feature.common.domain.models.UserDomainModel
import com.example.userbase.feature.usersList.data.local.sources.UsersListLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersListRepo @Inject constructor(private val usersListLocalDataSource: UsersListLocalDataSource) {

    suspend fun fetchUsers(
        lastUserId: Long? = null,
        pageSize: Int = 15
    ): Result<List<UserDomainModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val result = if (lastUserId == null) {
                    usersListLocalDataSource.getFirstUsersPage(pageSize)
                } else {
                    usersListLocalDataSource.getUsersAfter(lastUserId, pageSize)
                }
                Result.success(result.map { it.toDomain() })
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}