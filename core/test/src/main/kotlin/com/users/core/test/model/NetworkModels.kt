package com.users.core.test.model

import com.users.core.network.model.AddressNetworkModel
import com.users.core.network.model.CompanyNetworkModel
import com.users.core.network.model.GeolocationNetworkModel
import com.users.core.network.model.UserNetworkModel

fun networkUsers(size: Int) = List(size) { networkUser(it.toLong()) }

fun networkCompany(id: Long = 0) = CompanyNetworkModel(
    name = "Company $id",
    catchPhrase = "Catch phrase $id",
    businessStuff = "Business stuff $id"
)

fun networkUser(id: Long = 0) = UserNetworkModel(
    id = id,
    name = "User $id",
    username = "Username $id",
    email = "user$id@example.com",
    website = "Website $id",
    company = networkCompany(id),
    address = networkAddress(id),
    phone = "phone $id"
)

fun networkAddress(id: Long = 0) = AddressNetworkModel(
    street = "Address $id",
    suite = "Suite $id",
    city = "City $id",
    zipcode = "$id",
    geolocation = GeolocationNetworkModel(
        latitude = id.toDouble(),
        longitude = id.toDouble()
    )
)