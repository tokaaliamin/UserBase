package com.example.userbase.feature.addUser.domain.usecases

import com.example.userbase.feature.addUser.data.repos.UserFormRepo
import com.example.userbase.feature.common.domain.models.UserDomainModel
import com.example.userbase.feature.common.domain.models.toData
import javax.inject.Inject

class UserFormUseCase @Inject constructor(private val userFormRepo: UserFormRepo) {

    suspend operator fun invoke(userDomainModel: UserDomainModel): Result<Unit> {
        return userFormRepo.addUser(userDomainModel.toData())
    }
}