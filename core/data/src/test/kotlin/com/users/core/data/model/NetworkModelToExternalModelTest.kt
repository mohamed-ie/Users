package com.users.core.data.model

import com.users.core.network.model.asExternalModel
import com.users.core.test.model.networkAddress
import com.users.core.test.model.networkCompany
import com.users.core.test.model.networkUser
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class NetworkModelToExternalModelTest {
    @Test
    fun addressNetworkModelToExternalModel() {
        val networkAddress = networkAddress()
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
        val networkCompany = networkCompany()
        val externalCompany = networkCompany.asExternalModel()

        assertEquals(externalCompany?.name, networkCompany.name)
        assertEquals(externalCompany?.catchPhrase, networkCompany.catchPhrase)
        assertEquals(externalCompany?.businessStuff, networkCompany.businessStuff)
    }

    @Test
    fun userNetworkModelToExternalModel() {
        val networkUser = networkUser()
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