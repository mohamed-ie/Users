package com.users.core.test.model

import com.users.core.database.model.AddressEntity
import com.users.core.database.model.CompanyEntity
import com.users.core.database.model.PopulatedUser
import com.users.core.database.model.UserEntity

fun populatedUsers(size: Int) = List(size) { populatedUser(it.toLong()) }

fun populatedUser(id: Long = 0) = PopulatedUser(
    user = userEntity(id),
    company = companyEntity(id),
    address = addressEntity(id)
)

fun userEntity(id: Long = 0) = UserEntity(
    id = id,
    name = "User $id",
    username = "Username $id",
    email = "user$id@example.com",
    website = "Website $id",
    phone = "phone $id"
)

fun companyEntity(id: Long = 0) = CompanyEntity(
    id = id,
    name = "Company $id",
    catchPhrase = "Catch phrase $id",
    businessStuff = "Business stuff $id"
)

fun addressEntity(id: Long = 0) = AddressEntity(
    id = id,
    street = "Address $id",
    suite = "Suite $id",
    city = "City $id",
    zipcode = "$id",
    latitude = id.toDouble(),
    longitude = id.toDouble()
)