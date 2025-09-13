package com.example.userbase.feature.usersList.data.local.sources

import com.example.userbase.feature.common.data.local.database.AppDatabase
import com.example.userbase.feature.common.data.models.UserDateModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersListLocalDataSource @Inject constructor(private val database: AppDatabase) {
    suspend fun getFirstUsersPage(pageSize: Int): Flow<List<UserDateModel>> {
        return database.userDao().getFirstUsersPage(pageSize)
    }


    suspend fun getUsersAfter(lastUserId: Long, pageSize: Int): Flow<List<UserDateModel>> {
        return database.userDao().getUsersAfter(lastUserId, pageSize)
    }

}