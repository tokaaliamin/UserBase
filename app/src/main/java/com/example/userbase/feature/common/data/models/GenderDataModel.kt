package com.example.userbase.feature.common.data.models

import com.example.userbase.feature.common.domain.models.GenderDomainModel

enum class GenderDataModel {
    MALE, FEMALE
}

fun GenderDataModel.toDomain() = GenderDomainModel.valueOf(this.name)