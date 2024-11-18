package com.users.core.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.users.core.model.User

data class PopulatedUser(
    @Embedded
    val user: UserEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val company: CompanyEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val address: AddressEntity
)

fun PopulatedUser.asExternalModel() = User(
    id = user.id,
    name = user.name,
    username = user.username,
    email = user.email,
    phone = user.phone,
    website = user.website,
    address = address.asExternalModel(),
    company = company.asExternalModel(),
    isLiked = true
)