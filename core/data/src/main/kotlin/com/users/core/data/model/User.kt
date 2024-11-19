package com.users.core.data.model

import com.users.core.database.model.UserEntity
import com.users.core.model.User

internal fun User.asEntity() = UserEntity(
    id = id,
    name = name,
    username = username,
    email = email,
    phone = phone,
    website = website
)