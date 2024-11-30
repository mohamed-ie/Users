package com.users.core.test.model

import com.users.core.model.Address
import com.users.core.model.Company
import com.users.core.model.Geolocation
import com.users.core.model.User

fun users(size:Int) = List(size) { user(it.toLong()) }

fun user(id: Long = 0) = User(
    id = id,
    name = "User $id",
    username = "Username $id",
    email = "user$id@example.com",
    website = "Website $id",
    company = company(id),
    address = address(id),
    phone = "phone $id",
    isLiked = true
)

fun company(id: Long = 0) = Company(
    name = "Company $id",
    catchPhrase = "Catch phrase $id",
    businessStuff = "Business stuff $id"
)

fun address(id: Long = 0) = Address(
    street = "Address $id",
    suite = "Suite $id",
    city = "City $id",
    zipcode = "$id",
    geolocation = Geolocation(
        latitude = id.toDouble(),
        longitude = id.toDouble()
    )
)