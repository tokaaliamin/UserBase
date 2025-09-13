package com.example.userbase.feature.common.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.userbase.feature.common.domain.models.UserDomainModel

@Entity(tableName = "users")
data class UserDateModel(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "job_title") val jobTitle: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "gender") val genderModel: GenderDataModel
)

fun UserDateModel.toDomain() = UserDomainModel(
    name = name,
    jobTitle = jobTitle,
    age = age,
    genderModel = genderModel.toDomain(),
    id = id ?: 0
)
