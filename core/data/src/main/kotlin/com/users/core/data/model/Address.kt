package com.users.core.data.model

import com.users.core.database.model.AddressEntity
import com.users.core.model.Address

internal fun Address.asEntity() = AddressEntity(
    street = street,
    suite = suite,
    city = city,
    zipcode = zipcode,
    latitude = geolocation.latitude,
    longitude = geolocation.longitude
)