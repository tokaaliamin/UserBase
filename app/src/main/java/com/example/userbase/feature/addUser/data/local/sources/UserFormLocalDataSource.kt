package com.example.userbase.feature.addUser.data.local.sources

import com.example.userbase.feature.common.data.local.database.AppDatabase
import com.example.userbase.feature.common.data.models.UserDateModel
import javax.inject.Inject

class UserFormLocalDataSource @Inject constructor(private val database: AppDatabase) {
    suspend fun addUser(user: UserDateModel): Long {
        return database.userDao().addUser(user)
    }
}