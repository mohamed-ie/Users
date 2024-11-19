package com.users.core.data.model

import com.users.core.network.model.AddressNetworkModel
import com.users.core.network.model.CompanyNetworkModel
import com.users.core.network.model.GeolocationNetworkModel
import com.users.core.network.model.UserNetworkModel
import com.users.core.network.model.asExternalModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class NetworkModelToExternalModelTest {
    @Test
    fun addressNetworkModelToExternalModel() {
        val networkAddress = networkAddress
        val externalAddress = networkAddress.asExternalModel()

        assertEquals(externalAddress?.street, networkAddress.street)
        assertEquals(externalAddress?.suite, networkAddress.suite)
        assertEquals(externalAddress?.city, networkAddress.city)
        assertEquals(externalAddress?.zipcode, networkAddress.zipcode)
        assertEquals(externalAddress?.geolocation?.latitude, networkAddress.geolocation?.latitude)
        assertEquals(externalAddress?.geolocation?.longitude, networkAddress.geolocation?.longitude)
    }

    @Test
    fun companyNetworkModelToExternalModel() {
        val networkCompany = networkCompany
        val externalCompany = networkCompany.asExternalModel()

        assertEquals(externalCompany?.name, networkCompany.name)
        assertEquals(externalCompany?.catchPhrase, networkCompany.catchPhrase)
        assertEquals(externalCompany?.businessStuff, networkCompany.businessStuff)
    }

    @Test
    fun userNetworkModelToExternalModel() {
        val networkUser = networkUser
        val externalUser = networkUser.asExternalModel()

        assertNotNull(externalUser)
        assertEquals(externalUser?.id, networkUser.id)
        assertEquals(externalUser?.name, networkUser.name)
        assertEquals(externalUser?.username, networkUser.username)
        assertEquals(externalUser?.email, networkUser.email)
        assertEquals(externalUser?.website, networkUser.website)
        assertEquals(externalUser?.phone, networkUser.phone)
    }

}

private val networkUser
    get() = UserNetworkModel(
        id = 0,
        name = "User",
        username = "Username",
        email = "user@example.com",
        website = "Website",
        company = networkCompany,
        address = networkAddress,
        phone = "phone"
    )

private val networkCompany
    get() = CompanyNetworkModel(
        name = "Company",
        catchPhrase = "Catch phrase",
        businessStuff = "Business stuff"
    )

private val networkAddress
    get() = AddressNetworkModel(
        street = "Address",
        suite = "Suite",
        city = "City",
        zipcode = "123",
        geolocation = GeolocationNetworkModel(
            latitude = 2.2,
            longitude = 1.1
        )
    )