package com.example.userbase.feature.addUser.data.repos

import com.example.userbase.feature.addUser.data.local.sources.UserFormLocalDataSource
import com.example.userbase.feature.common.data.models.UserDateModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserFormRepo @Inject constructor(private val userFormLocalDataSource: UserFormLocalDataSource) {

    suspend fun addUser(
        user: UserDateModel
    ): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                userFormLocalDataSource.addUser(user)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}