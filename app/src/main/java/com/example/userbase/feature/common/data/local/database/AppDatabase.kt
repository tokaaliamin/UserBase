package com.example.userbase.feature.common.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.userbase.feature.common.data.local.daos.UserDao
import com.example.userbase.feature.common.data.models.UserDateModel

@Database(entities = [UserDateModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}