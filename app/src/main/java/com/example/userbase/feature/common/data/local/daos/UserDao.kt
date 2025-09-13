package com.example.userbase.feature.common.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.userbase.feature.common.data.models.UserDateModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id > :lastId ORDER BY id LIMIT :pageSize")
    fun getUsersAfter(lastId: Long, pageSize: Int): Flow<List<UserDateModel>>

    @Query("SELECT * FROM users ORDER BY id LIMIT :pageSize")
    fun getFirstUsersPage(pageSize: Int): Flow<List<UserDateModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserDateModel): Long
}