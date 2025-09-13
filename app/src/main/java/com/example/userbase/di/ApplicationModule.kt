package com.example.userbase.di

import android.content.Context
import androidx.room.Room
import com.example.userbase.feature.common.data.local.database.AppDatabase
import com.example.userbase.feature.usersList.data.local.sources.UsersListLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class ApplicationModule {

    companion object {
        @Provides
        fun provideUsersListLocalDataSource(db: AppDatabase) = UsersListLocalDataSource(db)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object SingleModule {
    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, AppDatabase::class.java, "usersDB")
        .build()
}