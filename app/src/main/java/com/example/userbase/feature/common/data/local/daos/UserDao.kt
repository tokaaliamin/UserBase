package com.example.userbase.feature.common.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.userbase.feature.common.data.models.UserDateModel

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id > :lastId ORDER BY id LIMIT :pageSize")
    suspend fun getUsersAfter(lastId: Long, pageSize: Int): List<UserDateModel>

    @Query("SELECT * FROM users ORDER BY id LIMIT :pageSize")
    suspend fun getFirstUsersPage(pageSize: Int): List<UserDateModel>
}