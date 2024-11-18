package com.users.core.data.model

import com.users.core.database.model.CompanyEntity
import com.users.core.model.Company

internal fun Company.asEntity() = CompanyEntity(
    name = name,
    catchPhrase = catchPhrase,
    businessStuff = businessStuff
)